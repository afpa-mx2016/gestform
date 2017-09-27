-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Mer 27 Septembre 2017 à 10:48
-- Version du serveur :  5.7.19-0ubuntu0.17.04.1
-- Version de PHP :  7.0.22-0ubuntu0.17.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gestform`
--

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
  `code` char(5) NOT NULL,
  `nom` varchar(128) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `formation`
--

INSERT INTO `formation` (`code`, `nom`, `date_debut`, `date_fin`) VALUES
('CR16', 'Crépier', '2016-10-03', '2017-05-23'),
('DL16', 'Développeur Logiciel', '2016-10-03', '2017-05-23');

-- --------------------------------------------------------

--
-- Structure de la table `module`
--

CREATE TABLE `module` (
  `id` int(11) NOT NULL,
  `nom` varchar(128) NOT NULL,
  `formation_code` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `module`
--

INSERT INTO `module` (`id`, `nom`, `formation_code`) VALUES
(1, 'Developpement Web', 'DL16'),
(3, 'Interface utilisateur', 'DL16'),
(4, 'Conception BDD', 'DL16'),
(9, 'kikou des iles', 'CR16'),
(10, 'Développer un site de gestion de contenu ou d\'e-commerce', 'DL16');

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE `personne` (
  `id` int(11) NOT NULL,
  `nom` varchar(128) NOT NULL,
  `prenom` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `personne`
--

INSERT INTO `personne` (`id`, `nom`, `prenom`) VALUES
(20, 'Duss', 'Jean Claude'),
(21, 'Heinstein', 'Albert');

-- --------------------------------------------------------

--
-- Structure de la table `result_ecf`
--

CREATE TABLE `result_ecf` (
  `acquis` tinyint(1) NOT NULL,
  `stagiaire_code` char(8) NOT NULL,
  `module_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `result_ecf`
--

INSERT INTO `result_ecf` (`acquis`, `stagiaire_code`, `module_id`) VALUES
(1, '125478MP', 1),
(0, '125478MP', 3),
(0, '125478MP', 4),
(0, 'd4512MPL', 1),
(0, 'd4512MPL', 3),
(0, 'd4512MPL', 4);

-- --------------------------------------------------------

--
-- Structure de la table `stagiaire`
--

CREATE TABLE `stagiaire` (
  `personne_id` int(11) NOT NULL,
  `code` char(8) NOT NULL,
  `formation_code` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `stagiaire`
--

INSERT INTO `stagiaire` (`personne_id`, `code`, `formation_code`) VALUES
(20, '125478MP', 'DL16'),
(21, 'd4512MPL', 'DL16');

--
-- Déclencheurs `stagiaire`
--
DELIMITER $$
CREATE TRIGGER `stag_init_ecf` AFTER INSERT ON `stagiaire` FOR EACH ROW INSERT INTO result_ecf (stagiaire_code, ecf_id, acquis) SELECT new.code, id, false FROM ecf WHERE formation_code = new.formation_code
$$
DELIMITER ;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`code`);

--
-- Index pour la table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`id`),
  ADD KEY `formation_code` (`formation_code`);

--
-- Index pour la table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `result_ecf`
--
ALTER TABLE `result_ecf`
  ADD PRIMARY KEY (`stagiaire_code`,`module_id`);

--
-- Index pour la table `stagiaire`
--
ALTER TABLE `stagiaire`
  ADD PRIMARY KEY (`personne_id`,`formation_code`),
  ADD KEY `personne_id` (`personne_id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `module`
--
ALTER TABLE `module`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT pour la table `personne`
--
ALTER TABLE `personne`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `module`
--
ALTER TABLE `module`
  ADD CONSTRAINT `module_ibfk_1` FOREIGN KEY (`formation_code`) REFERENCES `formation` (`code`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

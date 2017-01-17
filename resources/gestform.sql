-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 17, 2017 at 03:57 PM
-- Server version: 5.7.16-0ubuntu0.16.04.1
-- PHP Version: 7.0.8-0ubuntu0.16.04.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestform`
--

-- --------------------------------------------------------

--
-- Table structure for table `ecf`
--

CREATE TABLE `ecf` (
  `id` int(11) NOT NULL,
  `nom` varchar(128) NOT NULL,
  `formation_code` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ecf`
--

INSERT INTO `ecf` (`id`, `nom`, `formation_code`) VALUES
(1, 'Developpement Web', 'DL16');

-- --------------------------------------------------------

--
-- Table structure for table `formation`
--

CREATE TABLE `formation` (
  `code` char(5) NOT NULL,
  `nom` varchar(128) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `formation`
--

INSERT INTO `formation` (`code`, `nom`, `date_debut`, `date_fin`) VALUES
('DL16', 'Développeur Logiciel', '2016-10-03', '2017-05-23');

-- --------------------------------------------------------

--
-- Table structure for table `personne`
--

CREATE TABLE `personne` (
  `id` int(11) NOT NULL,
  `nom` varchar(128) NOT NULL,
  `prenom` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `personne`
--

INSERT INTO `personne` (`id`, `nom`, `prenom`) VALUES
(3, 'duclou', 'alfred'),
(4, 'duclou', 'alfred'),
(5, 'duclou', 'alfred'),
(6, 'duclou', 'alfred'),
(7, 'duclou', 'alfred'),
(8, 'duclou', 'alfred'),
(9, 'duclou', 'alfred'),
(13, 'duclou', 'alfred'),
(16, 'duclou', 'alfred');

-- --------------------------------------------------------

--
-- Table structure for table `result_ecf`
--

CREATE TABLE `result_ecf` (
  `acquis` tinyint(1) NOT NULL,
  `stagiaire_code` char(8) NOT NULL,
  `ecf_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `result_ecf`
--

INSERT INTO `result_ecf` (`acquis`, `stagiaire_code`, `ecf_id`) VALUES
(0, '001C0997', 1);

-- --------------------------------------------------------

--
-- Table structure for table `stagiaire`
--

CREATE TABLE `stagiaire` (
  `personne_id` int(11) NOT NULL,
  `code` char(8) NOT NULL,
  `formation_code` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stagiaire`
--

INSERT INTO `stagiaire` (`personne_id`, `code`, `formation_code`) VALUES
(16, '001C0997', 'DL16'),
(13, '001C0998', 'DL16'),
(9, '001C0999', 'DL16');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ecf`
--
ALTER TABLE `ecf`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`code`);

--
-- Indexes for table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `result_ecf`
--
ALTER TABLE `result_ecf`
  ADD PRIMARY KEY (`stagiaire_code`,`ecf_id`);

--
-- Indexes for table `stagiaire`
--
ALTER TABLE `stagiaire`
  ADD PRIMARY KEY (`code`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ecf`
--
ALTER TABLE `ecf`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `personne`
--
ALTER TABLE `personne`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

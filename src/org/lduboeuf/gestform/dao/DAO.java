/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lduboeuf.gestform.dao;

import java.util.List;

/**
 *
 * @author lionel
 */
public interface DAO<T> {
    public List<T> findAll();
    public T findBy(Object o);
    public void save(T t) throws Exception;
    public void update(T t) throws Exception;
    public void delete(T t) throws Exception; 
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.dal.repository;

import java.util.List;

/**
 *
 * @param <TEntity>
 * @param <TKey>
 */
public interface IRepository<TEntity, TKey> {
    public TEntity getById(TKey id);
    public List<TEntity> getAll();
    public TEntity addOrUpdate(TEntity item);
    public void delete(TKey id);
}

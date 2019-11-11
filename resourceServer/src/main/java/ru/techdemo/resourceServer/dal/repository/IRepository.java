/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.resourceServer.dal.repository;

import java.util.List;
import ru.techdemo.resourceServer.dal.entity.Entity;

/**
 *
 * @param <TEntity>
 * @param <TKey>
 */
public interface IRepository<TEntity extends Entity, TKey> {
    public TEntity getById(TKey id);
    public List<TEntity> getAll();
    public TEntity addOrUpdate(TEntity item);
    public void delete(TKey id);
}

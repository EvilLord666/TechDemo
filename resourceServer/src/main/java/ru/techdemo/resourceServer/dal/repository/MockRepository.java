/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.resourceServer.dal.repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ru.techdemo.resourceServer.dal.entity.Entity;


public class MockRepository<TEntity extends Entity> implements IRepository<TEntity, Long> {

    public MockRepository(List<TEntity> dataSource){
        this.dataSource = dataSource;
    }
    
    @Override
    public TEntity getById(Long id) {
        Optional<TEntity> optional = dataSource.stream().filter(item -> Objects.equals(item.getId(), id)).findAny();
        TEntity entity = optional.get();
        return entity;
    }

    @Override
    public List<TEntity> getAll() {
        return dataSource;
    }

    @Override
    public TEntity addOrUpdate(TEntity item) {
        if ((Long)item.getId() > 0)  // update
        {
            Entity<Long> entity = getById((Long)item.getId());
            if(entity == null)
                return null;
            int itemIndex = dataSource.indexOf(entity);
            dataSource.set(itemIndex, item);
        }
        else  // create
        {
            item.setId(getNextId());
            dataSource.add(item);
        }
        return item;
    }

    @Override
    public void delete(Long id) {
        dataSource.removeIf(item->Objects.equals(item.getId(), id));
    }
    
    private Long getNextId(){
        Long id = 0L;
        for(Entity<Long> entity : dataSource){
            if (entity.getId() > id)
                id = entity.getId();
        }
        return id + 1;
    }
    
    private final List<TEntity> dataSource;
}

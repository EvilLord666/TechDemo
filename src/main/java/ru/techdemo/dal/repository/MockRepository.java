/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.dal.repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ru.techdemo.dal.entity.Entity;

/**
 *
 * @author michael
 */
public class MockRepository implements IRepository<Entity<Long>, Long> {

    public MockRepository(List<Entity<Long>> dataSource){
        this.dataSource = dataSource;
    }
    
    @Override
    public Entity<Long> getById(Long id) {
        Optional<Entity<Long>> optional = dataSource.stream().filter(item -> Objects.equals(item.getId(), id)).findAny();
        Entity entity = optional.get();
        return entity;
    }

    @Override
    public List<Entity<Long>> getAll() {
        return dataSource;
    }

    @Override
    public Entity addOrUpdate(Entity<Long> item) {
        if (item.getId() > 0)  // update
        {
            Entity<Long> entity = getById(item.getId());
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
    
    private final List<Entity<Long>> dataSource;
}

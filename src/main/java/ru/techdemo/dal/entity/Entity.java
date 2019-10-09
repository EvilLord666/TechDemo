/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.dal.entity;

/**
 *
 * @param <TKey>
 */
public abstract class Entity<TKey> {
    
    public TKey getId(){
        return id;
    }
    
    public void setId(TKey id){
        this.id = id;
    }
    
    TKey id;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.dal.entity;

public class RoleEntity extends Entity<Long> {
    
    public RoleEntity(){
        
    }
    
    public RoleEntity(Long id, String roleName){
        setId(id);
        this.roleName = roleName;
    }
    
    public String getRoleName(){
        return roleName;
    }
    
    public void setRoleName(String roleName){
        this.roleName = roleName;
    }
    
    String roleName;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.entity;

import java.util.List;
import java.util.ArrayList;

public class UserEntity {
    
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public String getUserName(){
        return userName;
    }
    
    public void setUserName(String userName){
        this.userName = userName;
    }
    
    public String getPasswordHash(){
        return passwordHash;
    }
    
    public void setPasswordHash(String passwordHash){
        this.passwordHash = passwordHash;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public List<RoleEntity> getRoles(){
        return roles;
    }
    
    public void setRoles(List<RoleEntity> roles){
        this.roles = roles;
    }
    
    private Long id;
    private String userName;
    private String passwordHash;
    
    private String firstName;
    private String lastName;
    
    private List<RoleEntity> roles = new ArrayList<RoleEntity>();
}

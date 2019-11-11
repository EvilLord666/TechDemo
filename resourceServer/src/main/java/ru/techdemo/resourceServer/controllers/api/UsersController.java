/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.resourceServer.controllers.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.techdemo.resourceServer.dal.IApplicationDataContext;
import ru.techdemo.resourceServer.dal.entity.UserEntity;

@RestController
public class UsersController {
    
    @GetMapping("/api/users")
    public List<UserEntity> get(){
        List<UserEntity> users = dataContext.getUsersRepository().getAll();
        return users;
    }
    
    @GetMapping("/api/users/{id}")
    public UserEntity get(@PathVariable(required = true)Long id){
        UserEntity user = dataContext.getUsersRepository().getById(id);
        return user;
    }
    
    @Autowired
    IApplicationDataContext dataContext;
}

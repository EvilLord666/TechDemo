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
import ru.techdemo.resourceServer.dal.entity.RoleEntity;

@RestController
public class RolesController {
    
    @GetMapping("/api/roles")
    public List<RoleEntity> get(){
        List<RoleEntity> roles = dataContext.getRolesRepository().getAll();
        return roles;
    }
    
    @GetMapping("/api/roles/{id}")
    public RoleEntity get(@PathVariable(required = true)Long id){
        RoleEntity role = dataContext.getRolesRepository().getById(id);
        return role;
    }
    
    @Autowired
    IApplicationDataContext dataContext;
}

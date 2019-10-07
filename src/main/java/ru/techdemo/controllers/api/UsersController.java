/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    
    @RequestMapping("/api/users")
    public String get(){
        return "{\"userId\": \"0\", \"userName\": \"admin\"}";
    }
}

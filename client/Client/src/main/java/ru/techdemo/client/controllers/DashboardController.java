/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.client.controllers;

import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author michael
 */
public class DashboardController {

    private Object message;
    @RequestMapping("/dashboard")
    public String home(Map<String, Object> model) {
        model.put("message", "Application v.0.9.0");
        return "index";
    }
}

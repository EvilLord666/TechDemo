package ru.techdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/access")
public class AccessController {

    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public String logIn(ModelMap model){
        return "";
    }

    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public String logOut(ModelMap model){
        return "";
    }
}

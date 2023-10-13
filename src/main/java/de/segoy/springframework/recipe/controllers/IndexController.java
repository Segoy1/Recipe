package de.segoy.springframework.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String getIndexPage(){
        System.out.println("1..2...3....4.....5");
        return "index";
    }
}

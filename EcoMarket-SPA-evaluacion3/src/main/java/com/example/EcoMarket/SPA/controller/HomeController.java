package com.example.EcoMarket.SPA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Redirige a la UI de Swagger
        return "redirect:/swagger-ui.html";
    }

}
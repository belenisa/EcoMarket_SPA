package com.EcoMarket_SPA.demo.Controlador;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class Controlador {

    @GetMapping("/")
    public String index () {
        return "Hello World!";
    }
}

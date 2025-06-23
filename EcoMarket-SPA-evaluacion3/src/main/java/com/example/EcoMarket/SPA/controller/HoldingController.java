package com.example.EcoMarket.SPA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EcoMarket.SPA.model.Holding;
import com.example.EcoMarket.SPA.service.HoldingService;

@RestController
@RequestMapping("/api/v1/holding")
public class HoldingController {

    @Autowired
    private HoldingService holdingService;

    @GetMapping
    public List<Holding> listarHolding() {
        return holdingService.getHolding();
    }

    @PostMapping
    public Holding agregarHolding(@RequestBody Holding holding) {
        return holdingService.saveHolding(holding);
    }

    @GetMapping("/{id}")
    public Holding buscarHolding(@PathVariable int id) {
        return holdingService.getHoldingId(id);
    }

    @PutMapping("/{id}")
    public Holding actualizarHolding(@PathVariable int id, @RequestBody Holding holding) {
        holding.setId(id);
        return holdingService.updateHolding(holding);
    }

    @DeleteMapping("/{id}")
    public String eliminarHolding(@PathVariable int id) {
        return holdingService.deleteHolding(id);
    }
}

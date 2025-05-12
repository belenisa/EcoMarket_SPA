package com.example.EcoMarket.SPA.controller;

import com.example.EcoMarket.SPA.model.Inventario;
import com.example.EcoMarket.SPA.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public List<Inventario> listarInventario() {
        return inventarioService.getInventario();
    }

    @PostMapping
    public Inventario agregarInventario(@RequestBody Inventario inventario) {
        return inventarioService.saveInventario(inventario);
    }

    @GetMapping("/{id}")
    public Inventario buscarInventario(@PathVariable int id) {
        return inventarioService.getInventarioId(id);
    }

    @PutMapping("/{id}")
    public Inventario actualizarInventario(@PathVariable int id, @RequestBody Inventario inventario) {
        inventario.setId(id);
        return inventarioService.updateInventario(inventario);
    }

    @DeleteMapping("/{id}")
    public String eliminarInventario(@PathVariable int id) {
        return inventarioService.deleteInventario(id);
    }
}

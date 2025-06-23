package com.example.EcoMarket.SPA.controller;

import com.example.EcoMarket.SPA.model.Proveedor;
import com.example.EcoMarket.SPA.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<Proveedor> listarProveedores() {
        return proveedorService.getProveedores();
    }

    @PostMapping
    public Proveedor agregarProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.saveProveedor(proveedor);
    }

    @GetMapping("/{id}")
    public Proveedor buscarProveedor(@PathVariable int id) {
        return proveedorService.getProveedorId(id);
    }

    @PutMapping("/{id}")
    public Proveedor actualizarProveedor(@PathVariable int id, @RequestBody Proveedor proveedor) {
        proveedor.setId(id);
        return proveedorService.updateProveedor(proveedor);
    }

    @DeleteMapping("/{id}")
    public String eliminarProveedor(@PathVariable int id) {
        return proveedorService.deleteProveedor(id);
    }
}

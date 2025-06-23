package com.example.EcoMarket.SPA.controller;

import com.example.EcoMarket.SPA.model.Producto;
import com.example.EcoMarket.SPA.service.ProductoServiceV2;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/productos")
public class ProductoControllerV2 {

    @Autowired
    private ProductoServiceV2 productoServiceV2;

    //private final String usuarioAutorizado = "ADMIN";

    @GetMapping
    @Operation(summary = "Obtener lista de los productos", description = "Obtiene una lista de todos los productos")
    public List<Producto> listarProductos() {
        return productoServiceV2.getProductos();
    }

    @PostMapping
    public Producto agregarProducto(@RequestBody Producto producto) {
        return productoServiceV2.saveProducto(producto);
    }

    @GetMapping("/{id}")
    public Producto buscarProducto(@PathVariable int id) {
        return productoServiceV2.getProductoId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarProducto(@PathVariable int id, @RequestBody Producto producto, @RequestParam String userRole) {
        producto.setId(id);
        return productoServiceV2.updateProducto(producto, userRole);
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable int id) {
        return productoServiceV2.deleteProducto(id);
    }

    @GetMapping("/total")
    public int totalProductos() {
        return productoServiceV2.totalProductos();
    }
}



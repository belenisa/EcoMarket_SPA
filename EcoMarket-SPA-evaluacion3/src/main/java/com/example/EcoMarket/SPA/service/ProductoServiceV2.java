package com.example.EcoMarket.SPA.service;

import com.example.EcoMarket.SPA.model.Producto;
import com.example.EcoMarket.SPA.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceV2 {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto getProductoId(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    public ResponseEntity<Object> updateProducto(Producto producto, String userRole) {
        if (!"ADMIN".equals(userRole)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo ADMIN puede actualizar productos.");
        }
        return ResponseEntity.ok(productoRepository.save(producto));
    }

    public String deleteProducto(int id) {
        productoRepository.deleteById(id);
        return "Producto eliminado con éxito";
    }

    public int totalProductos() {
        return (int) productoRepository.count();
    }
}

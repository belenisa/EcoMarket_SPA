package com.example.EcoMarket.SPA.service;

import com.example.EcoMarket.SPA.model.Producto;
import com.example.EcoMarket.SPA.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

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

    public Producto updateProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public String deleteProducto(int id) {
        productoRepository.deleteById(id);
        return "Producto eliminado con éxito";
    }

    public int totalProductos() {
        return (int) productoRepository.count();
    }
}

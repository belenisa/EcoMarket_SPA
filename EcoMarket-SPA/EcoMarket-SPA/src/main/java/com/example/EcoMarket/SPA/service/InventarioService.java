package com.example.EcoMarket.SPA.service;

import com.example.EcoMarket.SPA.model.Inventario;
import com.example.EcoMarket.SPA.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> getInventario() {
        return inventarioRepository.findAll();
    }

    public Inventario saveInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public Inventario getInventarioId(int id) {
        return inventarioRepository.findById(id).orElse(null);
    }

    public Inventario updateInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public String deleteInventario(int id) {
        inventarioRepository.deleteById(id);
        return "Registro de inventario eliminado";
    }
}

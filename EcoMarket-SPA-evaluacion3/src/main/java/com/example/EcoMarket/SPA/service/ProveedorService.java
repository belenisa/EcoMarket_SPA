package com.example.EcoMarket.SPA.service;

import com.example.EcoMarket.SPA.model.Proveedor;
import com.example.EcoMarket.SPA.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> getProveedores() {
        return proveedorRepository.findAll();
    }

    public Proveedor saveProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public Proveedor getProveedorId(int id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    public Proveedor updateProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public String deleteProveedor(int id) {
        proveedorRepository.deleteById(id);
        return "Proveedor eliminado";
    }
}

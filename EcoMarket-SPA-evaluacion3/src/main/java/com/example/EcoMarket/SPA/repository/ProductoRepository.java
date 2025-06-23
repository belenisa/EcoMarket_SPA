package com.example.EcoMarket.SPA.repository;

import com.example.EcoMarket.SPA.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    // Puedes agregar métodos personalizados si los necesitas
}

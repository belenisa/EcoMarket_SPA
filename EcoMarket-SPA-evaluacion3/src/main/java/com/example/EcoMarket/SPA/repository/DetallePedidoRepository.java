package com.example.EcoMarket.SPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.EcoMarket.SPA.model.DetallePedido;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
}

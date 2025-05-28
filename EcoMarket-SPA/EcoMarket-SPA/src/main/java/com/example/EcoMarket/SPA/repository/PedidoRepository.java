package com.example.EcoMarket.SPA.repository;

import com.example.EcoMarket.SPA.model.Pedido;
import com.example.EcoMarket.SPA.model.Pedido.EstadoPedido;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    
// Buscar pedidos por usuario
    List<Pedido> findByUsuarioId(Integer usuarioId);

    // Buscar pedidos por estado
    List<Pedido> findByEstado(EstadoPedido estado);

    // Buscar pedidos dentro de un rango de fechas
    List<Pedido> findByFechaBetween(LocalDate inicio, LocalDate fin);

    // Verificar si un pedido existe por ID
    boolean existsById(Integer id);

}

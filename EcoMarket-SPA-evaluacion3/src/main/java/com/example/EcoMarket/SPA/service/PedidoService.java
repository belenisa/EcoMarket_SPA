package com.example.EcoMarket.SPA.service;

import com.example.EcoMarket.SPA.model.Pedido;
import com.example.EcoMarket.SPA.repository.PedidoRepository;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> getPedidos() {
        return pedidoRepository.findAll();
    }

    public ResponseEntity<Object> savePedido(Pedido pedido, String userRole) {
        if (!userRole.equals("CLIENTE")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo los clientes pueden agregar pedidos.");
        }
        return ResponseEntity.ok(pedidoRepository.save(pedido));
    }

    public ResponseEntity<Pedido> getPedidoId(int id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> updatePedido(Pedido pedido, String userRole) {
        if (!(userRole.equals("ADMIN") || userRole.equals("GERENTE") || userRole.equals("VENTAS"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo ADMIN, GERENTE y VENTAS pueden actualizar pedidos.");
        }
        return ResponseEntity.ok(pedidoRepository.save(pedido));
    }

    public ResponseEntity<String> deletePedido(int id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (!pedidoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado.");
        }

        pedidoRepository.delete(pedidoOptional.get());
        return ResponseEntity.ok("Pedido eliminado correctamente.");
    }

    public List<Pedido> getPedidosPorUsuario(Integer usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }
}


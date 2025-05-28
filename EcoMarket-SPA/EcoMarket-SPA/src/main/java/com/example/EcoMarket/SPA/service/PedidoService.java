package com.example.EcoMarket.SPA.service;

import com.example.EcoMarket.SPA.model.Pedido;
import com.example.EcoMarket.SPA.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ResponseEntity<Object> getPedidoId(int id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado"));
    }

    public ResponseEntity<Object> updatePedido(Pedido pedido, String userRole) {
        if (!(userRole.equals("ADMIN") || userRole.equals("GERENTE") || userRole.equals("VENTAS"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo ADMIN, GERENTE y VENTAS pueden actualizar pedidos.");
        }
        return ResponseEntity.ok(pedidoRepository.save(pedido));
    }

    public ResponseEntity<String> deletePedido(int id) {
        if (!pedidoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado.");
        }
        pedidoRepository.deleteById(id);
        return ResponseEntity.ok("Pedido eliminado correctamente.");
    }
}


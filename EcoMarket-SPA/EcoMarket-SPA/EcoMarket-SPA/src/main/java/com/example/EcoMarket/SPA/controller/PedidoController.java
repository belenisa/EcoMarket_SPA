package com.example.EcoMarket.SPA.controller;

import com.example.EcoMarket.SPA.model.Pedido;
import com.example.EcoMarket.SPA.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    private final String usuarioAutorizado = "CLIENTE";

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.getPedidos();
    }

    @PostMapping
    public ResponseEntity<Object> agregarPedido(@RequestBody Pedido pedido, @RequestParam String userRole) {
        if (!userRole.equals(usuarioAutorizado)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo los clientes pueden agregar pedidos.");
        }
        return pedidoService.savePedido(pedido, userRole);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedido(@PathVariable int id) {
        return pedidoService.getPedidoId(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Pedido> obtenerPedidosPorUsuario(@PathVariable Integer usuarioId) {
        return pedidoService.getPedidosPorUsuario(usuarioId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarPedido(@PathVariable int id, @RequestBody Pedido pedido, @RequestParam String userRole) {
        if (!(userRole.equals("ADMIN") || userRole.equals("GERENTE") || userRole.equals("VENTAS"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo ADMIN, GERENTE y VENTAS pueden actualizar pedidos.");
        }

        if (pedido.getEstado() == null) {
            return ResponseEntity.badRequest().body("El estado debe ser: PENDIENTE, ENVIADO, ENTREGADO");
        }

        pedido.setId(id);
        return pedidoService.updatePedido(pedido, userRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPedido(@PathVariable int id) {
        return pedidoService.deletePedido(id);
    }
}


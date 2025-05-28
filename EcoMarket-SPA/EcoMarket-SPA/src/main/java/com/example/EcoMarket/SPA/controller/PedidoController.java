package com.example.EcoMarket.SPA.controller;

import com.example.EcoMarket.SPA.model.Pedido;
import com.example.EcoMarket.SPA.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    private final String usuarioAutorizado = "CLIENTE";

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.getPedidos();
    }

    @PostMapping
    public ResponseEntity<Object> agregarPedido(@RequestBody Pedido pedido, @RequestParam String userRole) {
        if (!userRole.equals("CLIENTE")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo los clientes pueden agregar pedidos.");
        }
        return ResponseEntity.ok(pedidoService.savePedido(pedido));
    }

    @GetMapping("/{id}")
    public Pedido buscarPedido(@PathVariable int id) {
        return pedidoService.getPedidoId(id);
    }

    @PutMapping("/{id}")
    public Pedido actualizarPedido(@PathVariable int id, @RequestBody Pedido pedido) {
        pedido.setId(id);
        return pedidoService.updatePedido(pedido);
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
        return ResponseEntity.ok(pedidoService.updatePedido(pedido));
    }


    @DeleteMapping("/{id}")
    public String eliminarPedido(@PathVariable int id) {
        return pedidoService.deletePedido(id);
    }
}

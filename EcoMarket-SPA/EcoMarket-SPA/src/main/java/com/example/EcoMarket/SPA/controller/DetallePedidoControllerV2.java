package com.example.EcoMarket.SPA.controller;

import com.example.EcoMarket.SPA.model.DetallePedido;
import com.example.EcoMarket.SPA.service.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalles")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;
    private final String usuarioAutorizado = "ADMIN";

    @GetMapping
    public List<DetallePedido> listarDetalles() {
        return detallePedidoService.getDetalles();
    }

    @PostMapping
    public DetallePedido agregarDetalle(@RequestBody DetallePedido detalle) {
        return detallePedidoService.saveDetalle(detalle);
    }

    @GetMapping("/{id}")
    public DetallePedido buscarDetalle(@PathVariable int id) {
        return detallePedidoService.getDetalleId(id);
    }

    @PutMapping("/{id}")
    public DetallePedido actualizarDetalle(@PathVariable int id, @RequestBody DetallePedido detalle) {
        detalle.setId(id);
        return detallePedidoService.updateDetalle(detalle);
    }

    @DeleteMapping("/{id}")
    public String eliminarDetalle(@PathVariable int id, @RequestParam String userRole, @RequestBody DetallePedido detalle) {
        if (!(userRole.equals("ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo ADMIN pueden eliminar detalles.");
        }
        pedido.setId(id);
        return ResponseEntity.ok(detallePedidoService.updatePedido(detalle));
    }
}
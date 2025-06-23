package com.example.EcoMarket.SPA.controller;

import com.example.EcoMarket.SPA.model.DetallePedido;
import com.example.EcoMarket.SPA.service.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalles")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

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
    public ResponseEntity<String> eliminarDetalle(@PathVariable int id) {
        boolean eliminado = detallePedidoService.deleteDetalle(id);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Detalle no encontrado.");
        }
        return ResponseEntity.ok("Detalle eliminado correctamente.");
    }
}


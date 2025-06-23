package com.example.EcoMarket.SPA.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.EcoMarket.SPA.model.Pedido;
import com.example.EcoMarket.SPA.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/pedidos")
@Tag(name = "Pedidos", description = "Operaciones relacionadas con los pedidos")
public class PedidoControllerV2 {

    @Autowired
    private PedidoService pedidoService;

    //private final String usuarioAutorizado = "CLIENTE";

    public PedidoControllerV2(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    @Operation(summary = "Obtener lista de los pedidos", description = "Obtiene una lista de todos los pedidos con el producto y total")
    public List<Pedido> listarPedidos() {
        return pedidoService.getPedidos().stream()
                .map(Pedido::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    @Operation(summary = "Actualizar pedidos", description = "Solo el usuario cliente puede agregar pedidos")
    public ResponseEntity<Object> agregarPedido(@RequestBody Pedido pedido, @RequestParam String userRole) {
        if (!userRole.equals("CLIENTE")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo los clientes pueden agregar pedidos.");
        }
        return ResponseEntity.ok(pedidoService.savePedido(pedido, userRole));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedidos por id", description = "Busca un pedido por medio del id del usuario")
    public ResponseEntity<Pedido> buscarPedido(@PathVariable int id) {
        return pedidoService.getPedidoId(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Obtener lista de los pedidos por id", description = "Obtiene una lista de todos los pedidos por ID de usuario")
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
        return ResponseEntity.ok(pedidoService.updatePedido(pedido, userRole));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPedido(@PathVariable int id, @RequestParam String userRole) {
        if (!(userRole.equals("ADMIN") || userRole.equals("GERENTE"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo ADMIN y GERENTE pueden eliminar pedidos.");
        }

        return pedidoService.deletePedido(id);
    }   

}



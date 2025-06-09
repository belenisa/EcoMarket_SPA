package com.example.EcoMarket.SPA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EcoMarket.SPA.model.Pedido;
import com.example.EcoMarket.SPA.service.PedidoService;


@RestController
@RequestMapping("/api/v2/pedidos")
@Tag(name = "Pedidos", description = "Operaciones relacionadas con los pedidos")
public class PedidoControlerV2 {

    @Autowired
    private PedidoService pedidoService;
    private final String usuarioAutorizado = "CLIENTE";
    


    @GetMapping
    @Operation(summary = "Obtener lista de los pedidos", description = "Obtiene una lista de todos los pedidos")
    public List<Pedido> listarPedidos() {
        return pedidoService.getPedidos();
    }

    public void PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @Operation(summary = "Actualizar pedidos", description = "Solo el usuario cliente puede agragar pedidos")
    public ResponseEntity<Object> agregarPedido(@RequestBody Pedido pedido, @RequestParam String userRole) {
        if (!userRole.equals("CLIENTE")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo los clientes pueden agregar pedidos.");
        }
        return ResponseEntity.ok(pedidoService.savePedido(pedido));//revisar
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedidos por id", description = "Busca un pedido por medio dekl id del usuario")
    public Pedido buscarPedido(@PathVariable int id) {
        return pedidoService.getPedidoId(id);
    }

    
    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Obtener lista de los pedidos por id", description = "Obtiene una lista de todos los pedidos por ID de usuario")
        public List<Pedido> obtenerPedidosPorUsuario(@PathVariable Integer usuarioId) {
        return pedidoService.getPedidosPorUsuario(usuarioId);//revisar
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
    public String eliminarPedido(@PathVariable int id, @RequestBody Pedido pedido, @RequestParam String userRole) {
        if (!(userRole.equals("ADMIN") || userRole.equals("GERENTE"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo ADMIN y GERENTE pueden eliminar pedidos.");
        }
        pedidoService.deletePedido(id);
        return ResponseEntity.ok(pedidoService.deletePedido(pedido));
    }


}

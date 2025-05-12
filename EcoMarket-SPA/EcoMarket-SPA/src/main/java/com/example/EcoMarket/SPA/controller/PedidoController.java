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

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.getPedidos();
    }

    @PostMapping
    public Pedido agregarPedido(@RequestBody Pedido pedido) {
        return pedidoService.savePedido(pedido);
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

    @DeleteMapping("/{id}")
    public String eliminarPedido(@PathVariable int id) {
        return pedidoService.deletePedido(id);
    }
}

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
public class PedidoControlerV2 {

    @Autowired
    private final String usuarioAutorizado = "CLIENTE";

    @Autowired
    private PedidoService pedidoService;
    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.getPedidos();
        
    }

    public void PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }


    



}

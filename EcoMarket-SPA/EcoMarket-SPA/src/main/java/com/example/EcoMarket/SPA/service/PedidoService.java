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

    public Pedido savePedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido getPedidoId(int id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido updatePedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public String deletePedido(int id) {
        pedidoRepository.deleteById(id);
        return "Pedido eliminado correctamente";
    }
}

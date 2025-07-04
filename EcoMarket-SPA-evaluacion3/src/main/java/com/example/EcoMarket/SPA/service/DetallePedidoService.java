package com.example.EcoMarket.SPA.service;

import com.example.EcoMarket.SPA.model.DetallePedido;
import com.example.EcoMarket.SPA.repository.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public List<DetallePedido> getDetalles() {
        return detallePedidoRepository.findAll();
    }

    public DetallePedido saveDetalle(DetallePedido detalle) {
        return detallePedidoRepository.save(detalle);
    }

    public DetallePedido getDetalleId(int id) {
        return detallePedidoRepository.findById(id).orElse(null);
    }

    public DetallePedido updateDetalle(DetallePedido detalle) {
        return detallePedidoRepository.save(detalle);
    }

    public boolean deleteDetalle(int id) {
    Optional<DetallePedido> detalle = detallePedidoRepository.findById(id);
    if (detalle.isPresent()) {
        detallePedidoRepository.delete(detalle.get());
        return true;
    }
    return false;
    }


    
}

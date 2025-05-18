package com.example.EcoMarket.SPA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EcoMarket.SPA.model.Holding;
import com.example.EcoMarket.SPA.repository.HoldingRepository;

@Service
public class HoldingService {
    @Autowired
    private HoldingRepository holdingRepository;

    public List<Holding> getHolding() {
        return holdingRepository.findAll();
    }

    public Holding saveHolding(Holding holding) {
        return holdingRepository.save(holding);
    }

    public Holding getHoldingId(int id) {
        return holdingRepository.findById(id).orElse(null);
    }

    public Holding updateHolding(Holding holding) {
        return holdingRepository.save(holding);
    }

    public String deleteHolding(int id) {
        holdingRepository.deleteById(id);
        return "Holding eliminado";
    }
}

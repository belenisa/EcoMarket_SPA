package com.example.EcoMarket.SPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.EcoMarket.SPA.model.Holding;

@Repository
public interface HoldingRepository extends JpaRepository<Holding, Integer> {



}

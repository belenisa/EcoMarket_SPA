package main.java.com.example.EcoMarket.SPA.repository;

import com.example.EcoMarket.SPA.model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldingRepository extends JpaRepository<Holding, Integer> {



}

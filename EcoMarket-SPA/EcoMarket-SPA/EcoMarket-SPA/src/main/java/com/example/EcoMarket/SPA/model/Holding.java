package com.example.EcoMarket.SPA.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Holding")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Holding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comuna;
    private String region;
    private String direccion;

    
    @ManyToOne
    @JoinColumn(name = "inventario_id", nullable = false) // Clave foránea
    private Inventario inventario;


}

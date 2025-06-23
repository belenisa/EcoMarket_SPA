package com.example.EcoMarket.SPA.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ElementCollection
    private List<String> producto = new ArrayList<>();

    private String nombre;
    private String modelo;
    private int stock; // solo lo actualiza el administrador
    private double precio;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false) // Clave foránea
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false) // Clave foránea
    private Pedido pedido;

    
}

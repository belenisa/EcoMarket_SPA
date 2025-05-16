package com.example.EcoMarket.SPA.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String correo;
    public enum Rol {
    ADMIN, CLIENTE, GERENTE, VENTAS, LOGISTICA
    }

    @Enumerated(EnumType.STRING)
    @JsonProperty("rol")
    private Rol rol;
}

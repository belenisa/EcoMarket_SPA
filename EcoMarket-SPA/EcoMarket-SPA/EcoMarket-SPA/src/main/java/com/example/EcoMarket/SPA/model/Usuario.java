package com.example.EcoMarket.SPA.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
        ADMIN, CLIENTE, GERENTE, VENTAS, LOGISTICA, PROVEEDOR;

        @JsonCreator
        public static Rol fromString(String value) {
            if (value == null) return null;

            for (Rol rol : Rol.values()) {
                if (rol.name().equalsIgnoreCase(value)) { // Permite ADMIN y "admin"
                    return rol;
                }
            }
            return null; // Evita que Spring falle antes de validar
        }

        @JsonValue
        public String toJson() {
            return name();
        }
    }

    @Enumerated(EnumType.STRING)
    @JsonProperty("rol")
    private Rol rol;
    
}

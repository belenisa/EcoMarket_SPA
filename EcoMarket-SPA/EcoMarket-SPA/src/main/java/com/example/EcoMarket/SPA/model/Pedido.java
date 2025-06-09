package com.example.EcoMarket.SPA.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @ElementCollection
    private List<String> pedidos = new ArrayList<>();
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate fecha;

    private double total;

    public enum EstadoPedido {
    PENDIENTE, ENVIADO, ENTREGADO
    }// Ej: "pendiente", "enviado", "entregado"

    @JsonCreator
    public static EstadoPedido fromString(String value) {
        if (value == null) return null;
        for (EstadoPedido estado : EstadoPedido.values()) {
            if (estado.name().equalsIgnoreCase(value)) { 
                return estado;
            }
        }
        return null;
    }

    @JsonValue
    public String toJson() {
        return name();
    }

    @Enumerated(EnumType.STRING)
    @JsonProperty("estadoPedido")
    private EstadoPedido estado;

    
    @ManyToMany
    @JoinTable(name = "pedido_producto", joinColumns = @JoinColumn(name = "pedido_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
     private List<Producto> productos = new ArrayList<>();

     @ManyToOne
     @JoinColumn(name = "usuario_id", nullable = false)
     private Usuario usuario;

}

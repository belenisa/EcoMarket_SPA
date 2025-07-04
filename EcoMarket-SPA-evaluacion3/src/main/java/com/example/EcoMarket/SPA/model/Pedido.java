package com.example.EcoMarket.SPA.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

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
        PENDIENTE, ENVIADO, ENTREGADO;
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
    }


    @Enumerated(EnumType.STRING)
    @JsonProperty("estadoPedido")
    private EstadoPedido estado;

    
    @PrePersist
    @PreUpdate
    public void calcularTotal() {
        this.total = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
    }


    
    // Constructor copia
    public Pedido(Pedido otro) {
        this.id = otro.getId();
        this.fecha = otro.getFecha();
        this.total = otro.getTotal();
        this.estado = otro.getEstado();
        this.pedidos = new ArrayList<>(otro.getPedidos());
        this.productos = new ArrayList<>(otro.getProductos());
        this.usuario = otro.getUsuario(); 
    }

    
    @ManyToMany
    @JoinTable(name = "pedido_producto", joinColumns = @JoinColumn(name = "pedido_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private List<Producto> productos = new ArrayList<>();

     @ManyToOne
     @JoinColumn(name = "usuario_id", nullable = false)
     private Usuario usuario;

}

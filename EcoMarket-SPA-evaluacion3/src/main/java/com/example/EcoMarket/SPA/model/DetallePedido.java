package com.example.EcoMarket.SPA.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false) // Clave foránea
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false) // Clave foránea
    private Producto producto;

    private int cantidad;

}

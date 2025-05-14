package com.example.EcoMarket.SPA;

import com.example.EcoMarket.SPA.model.Inventario;
import com.example.demoDB.model.Comuna;

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

package com.EcoMarket_SPA.demo.Modulo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.*;

@EntityScan
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuloUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    
    private String nombre;
    private String correo;
    private String fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "tipoUsuario_id", nullable = false) // Clave foránea
    private tipoUsuario_id tipoUsuario;



    


}

package com.EcoMarket_SPA.demo.Modulo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuloUsuario {

    private String id;
    private String nombre;
    private String correo;
    private String fechaNacimiento;

}

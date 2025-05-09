package com.EcoMarket_SPA.demo.Repositorio;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.EcoMarket_SPA.demo.Modulo.ModuloUsuario;

@Repository
public class RepositorioUsuarios {
  private List<ModuloUsuario> litaUsuarios = new ArrayList<>();

  public List<ModuloUsuario> ObtenerUsuario(){
        return litaUsuarios;
    }

}

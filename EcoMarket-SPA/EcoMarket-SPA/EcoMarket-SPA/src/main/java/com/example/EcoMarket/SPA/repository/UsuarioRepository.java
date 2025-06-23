package com.example.EcoMarket.SPA.repository;

import com.example.EcoMarket.SPA.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Puedes agregar métodos personalizados como: findByCorreo(String correo)
}

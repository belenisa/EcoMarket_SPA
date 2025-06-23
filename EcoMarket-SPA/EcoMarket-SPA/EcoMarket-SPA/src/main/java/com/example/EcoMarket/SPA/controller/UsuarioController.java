package com.example.EcoMarket.SPA.controller;

import java.util.EnumSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EcoMarket.SPA.model.Usuario;
import com.example.EcoMarket.SPA.model.Usuario.Rol;
import com.example.EcoMarket.SPA.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.getUsuarios();
    }

    @PostMapping
    public ResponseEntity<Object> agregarUsuario(@RequestBody Usuario usuario) {
        Rol rol = usuario.getRol();
        System.out.println("Rol recibido: " + rol);
    
        if (!esRolValido(rol)) {
            System.out.println("Error: Rol inválido.");
            return ResponseEntity.badRequest().body("Rol no válido. Debe ser uno de: " + EnumSet.allOf(Rol.class));
        }

        return ResponseEntity.ok(usuarioService.saveUsuario(usuario));
    }



    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable int id) {
        return usuarioService.getUsuarioId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        if (usuario.getRol() == null || !esRolValido(usuario.getRol())) {
            return ResponseEntity.badRequest().body("Rol no válido. Debe ser uno de: ADMIN, CLIENTE, GERENTE, VENTAS, LOGISTICA, PROVEEDOR");
        }
        usuario.setId(id);
        return ResponseEntity.ok(usuarioService.updateUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        return usuarioService.deleteUsuario(id);
    }

    private boolean esRolValido(Rol rol) {
    return rol != null && EnumSet.allOf(Rol.class).contains(rol);
    }
}

package com.example.EcoMarket.SPA.controller;

import com.example.EcoMarket.SPA.model.Usuario;
import com.example.EcoMarket.SPA.model.Usuario.Rol;
import com.example.EcoMarket.SPA.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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
        if (!esRolValido(usuario.getRol())) {
            return ResponseEntity.badRequest().body("Rol no válido. Debe ser uno de: ADMIN, CLIENTE, GERENTE, VENTAS, LOGISTICA");
        }
        return ResponseEntity.ok(usuarioService.saveUsuario(usuario));
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable int id) {
        return usuarioService.getUsuarioId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        if (!esRolValido(usuario.getRol())) {
            return ResponseEntity.badRequest().body("Rol no válido. Debe ser uno de: ADMIN, CLIENTE, GERENTE, VENTAS, LOGISTICA");
        }
        usuario.setId(id);
        return ResponseEntity.ok(usuarioService.updateUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        return usuarioService.deleteUsuario(id);
    }

    private boolean esRolValido(String rol) {
    return Arrays.stream(Rol.values())
                 .anyMatch(r -> r.name().equalsIgnoreCase(rol));
    }

}

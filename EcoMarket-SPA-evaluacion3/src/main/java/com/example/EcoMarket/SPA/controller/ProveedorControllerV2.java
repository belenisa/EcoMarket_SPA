package com.example.EcoMarket.SPA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.EcoMarket.SPA.model.Proveedor;
import com.example.EcoMarket.SPA.model.Usuario;
import com.example.EcoMarket.SPA.service.ProveedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/proveedores")
@Tag(name = "ProveedoresV2", description = "Operaciones relacionadas con los proveedores")
public class ProveedorControllerV2 {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    @Operation(summary = "Obtener lista de los proveedores",
               description = "Obtiene una lista de todos los proveedores")
    public ResponseEntity<?> listarProveedores(
            @RequestParam("rol") Usuario.Rol rol) {

        if (!tieneAcceso(rol)) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Acceso denegado. Debe ser uno de: ADMIN, GERENTE, LOGISTICA, PROVEEDOR");
        }
        return ResponseEntity.ok(proveedorService.getProveedores());
    }

    @PostMapping
    @Operation(summary = "Agregar proveedor", description = "Agrega un nuevo proveedor")
    public ResponseEntity<?> agregarProveedor(
            @RequestBody Proveedor proveedor,
            @RequestParam("rol") Usuario.Rol rol) {

        if (!tieneAcceso(rol)) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Acceso denegado. Debe ser uno de: ADMIN, GERENTE, LOGISTICA, PROVEEDOR");
        }
        return ResponseEntity.ok(proveedorService.saveProveedor(proveedor));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar proveedor", description = "Busca un proveedor por ID")
    public ResponseEntity<?> buscarProveedor(
            @PathVariable int id,
            @RequestParam("rol") Usuario.Rol rol) {

        if (!tieneAcceso(rol)) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Acceso denegado. Debe ser uno de: ADMIN, GERENTE, LOGISTICA, PROVEEDOR");
        }
        return ResponseEntity.ok(proveedorService.getProveedorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar proveedor", description = "Actualiza un proveedor por ID")
    public ResponseEntity<?> actualizarProveedor(
            @PathVariable int id,
            @RequestBody Proveedor proveedor,
            @RequestParam("rol") Usuario.Rol rol) {

        if (!tieneAcceso(rol)) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Acceso denegado. Debe ser uno de: ADMIN, GERENTE, LOGISTICA, PROVEEDOR");
        }
        proveedor.setId(id);
        return ResponseEntity.ok(proveedorService.updateProveedor(proveedor));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar proveedor", description = "Elimina un proveedor por ID")
    public ResponseEntity<?> eliminarProveedor(
            @PathVariable int id,
            @RequestParam("rol") Usuario.Rol rol) {

        if (!tieneAcceso(rol)) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Acceso denegado. Debe ser uno de: ADMIN, GERENTE, LOGISTICA, PROVEEDOR");
        }
        return ResponseEntity.ok(proveedorService.deleteProveedor(id));
    }

    private boolean tieneAcceso(Usuario.Rol rol) {
        if (rol == null) return false;
        return switch (rol) {
            case ADMIN, GERENTE, LOGISTICA, PROVEEDOR -> true;
            default -> false;
        };
    }


}

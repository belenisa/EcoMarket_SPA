package com.example.EcoMarket.SPA.controller;

import java.util.List;

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

import com.example.EcoMarket.SPA.model.Holding;
import com.example.EcoMarket.SPA.repository.HoldingRepository;
import com.example.EcoMarket.SPA.service.HoldingService;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/holding")
@Tag(name = "HoldingV2", description = "Operaciones relacionadas con los holding")
public class HoldingControllerV2 {

    @Autowired
    private HoldingService holdingService;
    private HoldingRepository holdingRepository;
    private boolean isAdmin(String userRole) {
        return "ADMIN".equalsIgnoreCase(userRole);
    }

    @GetMapping
    @JsonView(Holding.Views.Basic.class)
    @Operation(summary = "Obtener lista de los holdings básicos",
               description = "Solo campos id, comuna, región y dirección")
    public List<Holding> listarHoldingsBasicos() {
        return holdingService.getHolding();
    }

    // ——— Vista completa ———
    @GetMapping("/full")
    @JsonView(Holding.Views.Full.class)
        public ResponseEntity<?> listarHoldingsFull(@RequestParam String userRole) {
            if (!isAdmin(userRole)) {
                return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Acceso denegado. Solo ADMIN puede ver holdings completos.");
            }
        return ResponseEntity.ok(holdingService.getHolding());
    }



    @PostMapping
    @Operation(summary = "Agregar un holding", description = "Agrega un holding")
    public ResponseEntity<Object> agregarHolding(@RequestBody Holding holding, @RequestParam String userRole) {
        if (!isAdmin(userRole)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo ADMIN puede agregar holding.");
        }
        return ResponseEntity.ok(holdingRepository.save(holding));
    }



    @GetMapping("/{id}")
    @Operation(summary = "Buscar un holding", description = "Buscar un holdingpor ID")
    public Holding buscarHolding(@PathVariable int id) {
        return holdingService.getHoldingId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un holding", description = "Actualizar un holding por ID")
    public ResponseEntity<?> actualizarHolding(@PathVariable int id, @RequestBody Holding cambios, @RequestParam("userRole") String userRole) {
        if (!"ADMIN".equalsIgnoreCase(userRole)) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Solo ADMIN puede actualizar holding.");
        }

        Holding actualizado = holdingService.updateHolding(id, cambios);
        return ResponseEntity.ok(actualizado);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un holding", description = "Eliminar un holding por ID")
    public ResponseEntity<Object> eliminarHolding(@PathVariable int id, @RequestParam String userRole) {
        if (!isAdmin(userRole)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo ADMIN puede eliminar holding.");
        }
        holdingRepository.deleteById(id);
        return ResponseEntity.ok("Holding eliminado");
    }
}

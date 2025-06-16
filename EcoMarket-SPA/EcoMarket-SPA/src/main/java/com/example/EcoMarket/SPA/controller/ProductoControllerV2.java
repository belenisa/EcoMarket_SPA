package main.java.com.example.EcoMarket.SPA.controller;

import com.example.EcoMarket.SPA.model.Pedido;
import com.example.EcoMarket.SPA.model.Producto;
import com.example.EcoMarket.SPA.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/productos")
public class ProductoControllerV2 {

    @Autowired
    private ProductoService productoService;
    private final String usuarioAutorizado = "ADMIN";

    @GetMapping
    @Operation(summary = "Obtener lista de los productos", description = "Obtiene una lista de todos los productos")
    public List<Producto> listarProductos() {
        return productoService.getProductos();
    }

    @PostMapping
    public Producto agregarProducto(@RequestBody Producto producto) {
        return productoService.saveProducto(producto);
    }

    @GetMapping("/{id}")
    public Producto buscarProducto(@PathVariable int id) {
        return productoService.getProductoId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarProducto(@PathVariable int id, @RequestBody Producto producto, @RequestParam String userRole) {
         if (!(userRole.equals("ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo ADMIN pueden actualizar pedidos.");
        }
        pedido.setId(id);
        return ResponseEntity.ok(productoService.updatePedido(producto));
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable int id) {
        return productoService.deleteProducto(id);
    }

    @GetMapping("/total")
    public int totalProductos() {
        return productoService.totalProductos();
    }
}


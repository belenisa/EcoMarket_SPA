package com.example.EcoMarket.SPA;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.EcoMarket.SPA.model.DetallePedido;
import com.example.EcoMarket.SPA.model.Holding;
import com.example.EcoMarket.SPA.model.Inventario;
import com.example.EcoMarket.SPA.model.Pedido;
import com.example.EcoMarket.SPA.model.Producto;
import com.example.EcoMarket.SPA.model.Proveedor;
import com.example.EcoMarket.SPA.model.Usuario;
import com.example.EcoMarket.SPA.repository.DetallePedidoRepository;
import com.example.EcoMarket.SPA.repository.HoldingRepository;
import com.example.EcoMarket.SPA.repository.InventarioRepository;
import com.example.EcoMarket.SPA.repository.PedidoRepository;
import com.example.EcoMarket.SPA.repository.ProductoRepository;
import com.example.EcoMarket.SPA.repository.ProveedorRepository;
import com.example.EcoMarket.SPA.repository.UsuarioRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired private JdbcTemplate jdbcTemplate; //recordar eliminar

    @Autowired private HoldingRepository holdingRepository;

    @Autowired private UsuarioRepository usuarioRepository;

    @Autowired private ProveedorRepository proveedorRepository;

    @Autowired private ProductoRepository productoRepository;

    @Autowired private PedidoRepository pedidoRepository;

    @Autowired private DetallePedidoRepository detallePedidoRepository;

    @Autowired private InventarioRepository inventarioRepository;


    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        detallePedidoRepository.deleteAll();              // elimina detalle_pedido
        jdbcTemplate.execute("DELETE FROM pedido_producto"); // limpia join pedido–producto
        holdingRepository.deleteAll();                    // elimina holdings
        inventarioRepository.deleteAll();                 // elimina inventarios
        productoRepository.deleteAll();                   // elimina productos
        pedidoRepository.deleteAll();                     // elimina pedidos
        proveedorRepository.deleteAll();                  // elimina proveedores
        usuarioRepository.deleteAll();

        jdbcTemplate.execute("ALTER TABLE detalle_pedido AUTO_INCREMENT = 1");
        jdbcTemplate.execute("ALTER TABLE pedidos AUTO_INCREMENT = 1");
        jdbcTemplate.execute("ALTER TABLE productos AUTO_INCREMENT = 1");
        jdbcTemplate.execute("ALTER TABLE inventario AUTO_INCREMENT = 1");
        jdbcTemplate.execute("ALTER TABLE proveedor AUTO_INCREMENT = 1");
        jdbcTemplate.execute("ALTER TABLE holding AUTO_INCREMENT = 1");
        jdbcTemplate.execute("ALTER TABLE usuarios AUTO_INCREMENT = 1");



        // ——————————————————————————————
        // 1) Crear Usuarios + Proveedores
        // ——————————————————————————————
        List<Usuario> usuariosProveedores = new ArrayList<>();
        Usuario.Rol[] todosRoles = Usuario.Rol.values();

        // 1.1) Usuarios
        for (int i = 0; i < 10; i++) {
            Usuario u = new Usuario();
            u.setNombre(faker.name().fullName());
            u.setCorreo(faker.internet().emailAddress());
            u.setRol(todosRoles[random.nextInt(todosRoles.length)]);
            usuarioRepository.save(u);

            if (u.getRol() == Usuario.Rol.PROVEEDOR) {
                usuariosProveedores.add(u);
            }
        }

        // 1.2) Proveedores (para cada usuario de rol PROVEEDOR)
        for (Usuario uProv : usuariosProveedores) {
            Proveedor p = new Proveedor();
            p.setRut(faker.idNumber().valid());
            p.setUsuario(uProv);  // ¡relación no nula!
            // puedes omitir setNombre/setCorreo si ya los manejas desde Usuario
            proveedorRepository.save(p);
        }

        // Ahora hay proveedores con usuario asignado
        List<Proveedor> proveedoresExistentes = proveedorRepository.findAll();

        // ——————————————————————————————
        // 2) Crear Holdings
        // ——————————————————————————————
        List<String> comunas  = List.of("Santiago", "Valparaíso", "Antofagasta");
        List<String> regiones = List.of("Región Metropolitana", "Valparaíso", "Antofagasta");

        for (int i = 0; i < comunas.size(); i++) {
            // 2.1) Inventario asociado al holding (sin producto aún)
            Inventario inv = new Inventario();
            inv.setProducto(null);
            inv.setCantidadDisponible(random.nextInt(100));

            // 2.2) Holding
            Holding h = new Holding();
            h.setComuna(comunas.get(i));
            h.setRegion(regiones.get(i));
            h.setDireccion(faker.address().streetAddress());
            h.setInventario(inv);
            h.setProveedor(proveedoresExistentes.get(i % proveedoresExistentes.size()));

            holdingRepository.save(h);
        }

        // ——————————————————————————————
        // 3) Crear Productos
        // ——————————————————————————————
        if (productoRepository.count() == 0) {
            List<Proveedor> allProveedores = proveedorRepository.findAll();
            //List<Pedido>    allPedidos      = pedidoRepository.findAll(); // vacío ahora

            for (int i = 0; i < 10; i++) {
                Producto prod = new Producto();
                prod.setNombre(faker.commerce().productName());
                prod.setModelo(faker.bothify("MDL-???-####"));
                prod.setStock(1 + random.nextInt(100));
                prod.setPrecio(Double.parseDouble(faker.commerce().price()));
                prod.setProveedor(allProveedores.get(random.nextInt(allProveedores.size())));
                // No le asignamos pedido aún; se hará después
                productoRepository.save(prod);
            }
        }

        // ——————————————————————————————
        // 4) Crear Pedidos
        // ——————————————————————————————
        List<Usuario>  allUsuarios  = usuarioRepository.findAll();
        List<Producto> allProductos = productoRepository.findAll();
        List<Pedido>   createdPedidos = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Pedido ped = new Pedido();
            ped.setFecha(LocalDate.now().minusDays(random.nextInt(30)));
            ped.setEstado(Pedido.EstadoPedido.values()[random.nextInt(Pedido.EstadoPedido.values().length)]);
            ped.setUsuario(allUsuarios.get(random.nextInt(allUsuarios.size())));
            // Agregar algunos productos al pedido
            int nItems = 1 + random.nextInt(5);
            for (int k = 0; k < nItems; k++) {
                Producto choice = allProductos.get(random.nextInt(allProductos.size()));
                if (!ped.getProductos().contains(choice)) {
                    ped.getProductos().add(choice);
                }
            }
            pedidoRepository.save(ped);
            createdPedidos.add(ped);
        }

        // ——————————————————————————————
        // 5) Crear DetallePedido
        // ——————————————————————————————
        for (Pedido ped : createdPedidos) {
            for (Producto prod : ped.getProductos()) {
                DetallePedido det = new DetallePedido();
                det.setPedido(ped);
                det.setProducto(prod);
                int qty = 1 + random.nextInt(10);
                det.setCantidad(qty);
                // precioTotal se almacena de forma manual o con @PrePersist en Pedido
                det.setPrecioTotal(prod.getPrecio() * qty);
                detallePedidoRepository.save(det);
            }
        }

        // ——————————————————————————————
        // 6) Inventario de Producto (solo si no existe)
        // ——————————————————————————————
        for (Producto prod : productoRepository.findAll()) {
            if (!inventarioRepository.existsByProducto(prod)) {
                Inventario invP = new Inventario();
                invP.setProducto(prod);
                invP.setCantidadDisponible(random.nextInt(prod.getStock() + 1));
                inventarioRepository.save(invP);
            }
        }
    }
}
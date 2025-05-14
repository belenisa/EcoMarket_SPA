package com.example.EcoMarket.SPA.repository;

import com.example.EcoMarket.SPA.model.DetallePedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

    List<DetallePedido> findAll();

    @Query("Select dp FROM DetallePedido dp WHERE dp.id = id")
    List<DetallePedido> buscaPorId(@Param("id") String id);

    //@Query(value = "SELECT * FROM DetallePedido WHERE id = id")
    //DetallePedido buscaPorId(@Param("id") String id);

    @Modifying
    @Transactional
    @Query("INSERT INTO DetallePedido (id, descripcion, cantidad, precioUnitario) VALUES (:id, :descripcion, :cantidad, :precioUnitario)")
    void guardarDetalle(@Param("id") Integer id, @Param("cantidad") int cantidad, @Param("precioUnitario") double precioUnitario);

    @Modifying
    @Transactional
    @Query("UPDATE DetallePedido dp SET dp.cantidad = :cantidad, dp.precioUnitario = :precioUnitario WHERE dp.id = :id")
    void actualizarDetalle(@Param("id") Integer id, @Param("cantidad") int cantidad, @Param("precioUnitario") double precioUnitario);

    @Modifying
    @Transactional
    @Query("DELETE FROM DetallePedido dp WHERE dp.id = :id")
    void eliminarDetalle(@Param("id") Integer id);
}

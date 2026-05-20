package com.ernestodelarosa.ventas.repository;

import com.ernestodelarosa.ventas.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Integer> {
    List<Productos> findByEstado(Integer estado);
    List<Productos> findByNombreProductoContainingIgnoreCase(String nombre);

    // Consulta dinámica nativa de Spring Data JPA para filtros de smartphones
    @Query("SELECT p FROM Productos p WHERE p.estado = 1 AND " +
            "(:marca IS NULL OR LOWER(p.marca) LIKE LOWER(CONCAT('%', :marca, '%'))) AND " +
            "(:modelo IS NULL OR LOWER(p.modelo) LIKE LOWER(CONCAT('%', :modelo, '%'))) AND " +
            "(:color IS NULL OR LOWER(p.color) LIKE LOWER(CONCAT('%', :color, '%')))")
    List<Productos> buscarCelular(
            @Param("marca") String marca,
            @Param("modelo") String modelo,
            @Param("color") String color
    );
}
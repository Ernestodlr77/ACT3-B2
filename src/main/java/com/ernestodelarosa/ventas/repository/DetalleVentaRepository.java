package com.ernestodelarosa.ventas.repository;

import com.ernestodelarosa.ventas.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
    List<DetalleVenta> findByVentaCodigoVenta(Integer codigoVenta);
    List<DetalleVenta> findByProductoCodigoProducto(Integer codigoProducto);
}
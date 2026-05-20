package com.ernestodelarosa.ventas.service;

import com.ernestodelarosa.ventas.entity.DetalleVenta;

import java.util.List;

public interface DetalleVentaService {
    List<DetalleVenta> findAll();
    DetalleVenta findById(Integer codigoDetalleVenta);
    DetalleVenta save(DetalleVenta detalleVenta);
    DetalleVenta update(Integer codigoDetalleVenta, DetalleVenta detalleVenta);
    void delete(Integer codigoDetalleVenta);
    List<DetalleVenta> findByVenta(Integer codigoVenta);
}
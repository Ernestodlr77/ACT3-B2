package com.ernestodelarosa.ventas.service;

import com.ernestodelarosa.ventas.entity.Ventas;

import java.util.List;

public interface VentasService {
    List<Ventas> findAll();
    Ventas findById(Integer codigoVenta);
    Ventas save(Ventas ventas);
    Ventas update(Integer codigoVenta, Ventas ventas);
    void delete(Integer codigoVenta);
    List<Ventas> findByCliente(Integer dpiCliente);
    List<Ventas> findByUsuario(Integer codigoUsuario);
}
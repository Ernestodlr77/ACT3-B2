package com.ernestodelarosa.ventas.service;

import com.ernestodelarosa.ventas.entity.Productos;

import java.util.List;

public interface ProductosService {
    List<Productos> findAll();
    Productos findById(Integer codigoProducto);
    Productos save(Productos productos);
    Productos update(Integer codigoProducto, Productos productos);
    void delete(Integer codigoProducto);
    List<Productos> findByNombre(String nombre);
    List<Productos> buscarCelular(String marca, String modelo, String color);
}
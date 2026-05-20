package com.ernestodelarosa.ventas.service;

import com.ernestodelarosa.ventas.entity.Clientes;

import java.util.List;

public interface ClientesService {
    List<Clientes> findAll();
    Clientes findById(Integer dpiCliente);
    Clientes save(Clientes clientes);
    Clientes update(Integer dpiCliente, Clientes clientes);
    void delete(Integer dpiCliente);
}
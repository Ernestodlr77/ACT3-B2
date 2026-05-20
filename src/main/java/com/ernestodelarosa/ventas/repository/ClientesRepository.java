package com.ernestodelarosa.ventas.repository;

import com.ernestodelarosa.ventas.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Integer> {
    Optional<Clientes> findByDpiCliente(Integer dpiCliente);
    boolean existsByDpiCliente(Integer dpiCliente);
}
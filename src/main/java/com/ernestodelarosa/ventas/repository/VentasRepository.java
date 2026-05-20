package com.ernestodelarosa.ventas.repository;

import com.ernestodelarosa.ventas.entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentasRepository extends JpaRepository<Ventas, Integer> {
    List<Ventas> findByFechaVenta(LocalDate fecha);
    List<Ventas> findByClienteDpiCliente(Integer dpiCliente);
    List<Ventas> findByUsuarioCodigoUsuario(Integer codigoUsuario);
}
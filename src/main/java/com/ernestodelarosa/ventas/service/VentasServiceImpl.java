package com.ernestodelarosa.ventas.service;

import com.ernestodelarosa.ventas.entity.Ventas;
import com.ernestodelarosa.ventas.exception.ResourceNotFoundException;
import com.ernestodelarosa.ventas.repository.VentasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VentasServiceImpl implements VentasService {

    private final VentasRepository ventasRepository;

    @Override
    public List<Ventas> findAll() {
        return ventasRepository.findAll();
    }

    @Override
    public Ventas findById(Integer codigoVenta) {
        return ventasRepository.findById(codigoVenta)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con ID: " + codigoVenta));
    }

    @Override
    @Transactional
    public Ventas save(Ventas ventas) {
        return ventasRepository.save(ventas);
    }

    @Override
    @Transactional
    public Ventas update(Integer codigoVenta, Ventas ventasDetails) {
        Ventas ventas = findById(codigoVenta);

        ventas.setFechaVenta(ventasDetails.getFechaVenta());
        ventas.setTotal(ventasDetails.getTotal());
        ventas.setEstado(ventasDetails.getEstado());
        ventas.setCliente(ventasDetails.getCliente());
        ventas.setUsuario(ventasDetails.getUsuario());

        return ventasRepository.save(ventas);
    }

    @Override
    @Transactional
    public void delete(Integer codigoVenta) {
        Ventas ventas = findById(codigoVenta);
        ventasRepository.delete(ventas);
    }

    @Override
    public List<Ventas> findByCliente(Integer dpiCliente) {
        return ventasRepository.findByClienteDpiCliente(dpiCliente);
    }

    @Override
    public List<Ventas> findByUsuario(Integer codigoUsuario) {
        return ventasRepository.findByUsuarioCodigoUsuario(codigoUsuario);
    }
}
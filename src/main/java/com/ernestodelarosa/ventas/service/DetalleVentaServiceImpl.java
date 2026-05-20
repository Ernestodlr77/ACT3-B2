package com.ernestodelarosa.ventas.service;

import com.ernestodelarosa.ventas.entity.DetalleVenta;
import com.ernestodelarosa.ventas.exception.ResourceNotFoundException;
import com.ernestodelarosa.ventas.repository.DetalleVentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DetalleVentaServiceImpl implements DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    @Override
    public List<DetalleVenta> findAll() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta findById(Integer codigoDetalleVenta) {
        return detalleVentaRepository.findById(codigoDetalleVenta)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle de venta no encontrado con ID: " + codigoDetalleVenta));
    }

    @Override
    @Transactional
    public DetalleVenta save(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    @Transactional
    public DetalleVenta update(Integer codigoDetalleVenta, DetalleVenta detalleVentaDetails) {
        DetalleVenta detalleVenta = findById(codigoDetalleVenta);

        detalleVenta.setCantidad(detalleVentaDetails.getCantidad());
        detalleVenta.setPrecioUnitario(detalleVentaDetails.getPrecioUnitario());
        detalleVenta.setSubtotal(detalleVentaDetails.getSubtotal());
        detalleVenta.setProducto(detalleVentaDetails.getProducto());
        detalleVenta.setVenta(detalleVentaDetails.getVenta());

        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    @Transactional
    public void delete(Integer codigoDetalleVenta) {
        DetalleVenta detalleVenta = findById(codigoDetalleVenta);
        detalleVentaRepository.delete(detalleVenta);
    }

    @Override
    public List<DetalleVenta> findByVenta(Integer codigoVenta) {
        return detalleVentaRepository.findByVentaCodigoVenta(codigoVenta);
    }
}
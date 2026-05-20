package com.ernestodelarosa.ventas.service;

import com.ernestodelarosa.ventas.entity.Productos;
import com.ernestodelarosa.ventas.exception.ResourceNotFoundException;
import com.ernestodelarosa.ventas.repository.ProductosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductosServiceImpl implements ProductosService {

    private final ProductosRepository productosRepository;

    @Override
    public List<Productos> findAll() {
        return productosRepository.findAll();
    }

    @Override
    public Productos findById(Integer codigoProducto) {
        return productosRepository.findById(codigoProducto)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + codigoProducto));
    }

    @Override
    @Transactional
    public Productos save(Productos productos) {
        return productosRepository.save(productos);
    }

    @Override
    @Transactional
    public Productos update(Integer codigoProducto, Productos productosDetails) {
        Productos productos = findById(codigoProducto);

        productos.setNombreProducto(productosDetails.getNombreProducto());
        productos.setMarca(productosDetails.getMarca());
        productos.setModelo(productosDetails.getModelo());
        productos.setRam(productosDetails.getRam());
        productos.setAlmacenamiento(productosDetails.getAlmacenamiento());
        productos.setColor(productosDetails.getColor());
        productos.setPrecio(productosDetails.getPrecio());
        productos.setStock(productosDetails.getStock());
        productos.setEstado(productosDetails.getEstado());

        return productosRepository.save(productos);
    }

    @Override
    @Transactional
    public void delete(Integer codigoProducto) {
        Productos productos = findById(codigoProducto);
        productosRepository.delete(productos);
    }

    @Override
    public List<Productos> findByNombre(String nombre) {
        return productosRepository.findByNombreProductoContainingIgnoreCase(nombre);
    }

    @Override
    public List<Productos> buscarCelular(String marca, String modelo, String color) {
        return productosRepository.buscarCelular(marca, modelo, color);
    }
}
package com.ernestodelarosa.ventas.controller;

import com.ernestodelarosa.ventas.entity.Productos;
import com.ernestodelarosa.ventas.service.ProductosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductosController {

    private final ProductosService productosService;

    @GetMapping
    public ResponseEntity<List<Productos>> getAll() {
        return ResponseEntity.ok(productosService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Productos> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(productosService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Productos> create(@Valid @RequestBody Productos productos) {
        return new ResponseEntity<>(productosService.save(productos), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Productos> update(@PathVariable Integer id, @Valid @RequestBody Productos productos) {
        return ResponseEntity.ok(productosService.update(id, productos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productosService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Productos>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(productosService.findByNombre(nombre));
    }

    // Endpoint adicional especializado en buscar características de celulares
    @GetMapping("/buscar-avanzado")
    public ResponseEntity<List<Productos>> buscarCelular(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) String color) {
        return ResponseEntity.ok(productosService.buscarCelular(marca, modelo, color));
    }
}
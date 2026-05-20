package com.ernestodelarosa.ventas.controller;

import com.ernestodelarosa.ventas.entity.DetalleVenta;
import com.ernestodelarosa.ventas.service.DetalleVentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-venta")
@RequiredArgsConstructor
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    @GetMapping
    public ResponseEntity<List<DetalleVenta>> getAll() {
        return ResponseEntity.ok(detalleVentaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(detalleVentaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DetalleVenta> create(@Valid @RequestBody DetalleVenta detalleVenta) {
        return new ResponseEntity<>(detalleVentaService.save(detalleVenta), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVenta> update(@PathVariable Integer id, @Valid @RequestBody DetalleVenta detalleVenta) {
        return ResponseEntity.ok(detalleVentaService.update(id, detalleVenta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        detalleVentaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/venta/{codigoVenta}")
    public ResponseEntity<List<DetalleVenta>> findByVenta(@PathVariable Integer codigoVenta) {
        return ResponseEntity.ok(detalleVentaService.findByVenta(codigoVenta));
    }
}
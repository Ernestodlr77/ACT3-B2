package com.ernestodelarosa.ventas.controller;

import com.ernestodelarosa.ventas.entity.Ventas;
import com.ernestodelarosa.ventas.service.VentasService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentasController {

    private final VentasService ventasService;

    @GetMapping
    public ResponseEntity<List<Ventas>> getAll() {
        return ResponseEntity.ok(ventasService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ventas> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(ventasService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Ventas> create(@Valid @RequestBody Ventas ventas) {
        return new ResponseEntity<>(ventasService.save(ventas), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ventas> update(@PathVariable Integer id, @Valid @RequestBody Ventas ventas) {
        return ResponseEntity.ok(ventasService.update(id, ventas));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ventasService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{dpi}")
    public ResponseEntity<List<Ventas>> findByCliente(@PathVariable Integer dpi) {
        return ResponseEntity.ok(ventasService.findByCliente(dpi));
    }
}
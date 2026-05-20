package com.ernestodelarosa.ventas.controller;

import com.ernestodelarosa.ventas.entity.Clientes;
import com.ernestodelarosa.ventas.service.ClientesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClientesController {

    private final ClientesService clientesService;

    @GetMapping
    public ResponseEntity<List<Clientes>> getAll() {
        return ResponseEntity.ok(clientesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clientes> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(clientesService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Clientes> create(@Valid @RequestBody Clientes clientes) {
        return new ResponseEntity<>(clientesService.save(clientes), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clientes> update(@PathVariable Integer id, @Valid @RequestBody Clientes clientes) {
        return ResponseEntity.ok(clientesService.update(id, clientes));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clientesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
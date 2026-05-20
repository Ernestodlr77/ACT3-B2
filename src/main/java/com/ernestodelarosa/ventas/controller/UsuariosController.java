package com.ernestodelarosa.ventas.controller;

import com.ernestodelarosa.ventas.entity.Usuarios;
import com.ernestodelarosa.ventas.service.UsuariosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuariosController {

    private final UsuariosService usuariosService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Usuarios>> getAll() {
        return ResponseEntity.ok(usuariosService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuarios> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(usuariosService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuarios> create(@Valid @RequestBody Usuarios usuarios) {
        return new ResponseEntity<>(usuariosService.save(usuarios), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuarios> update(@PathVariable Integer id, @Valid @RequestBody Usuarios usuarios) {
        return ResponseEntity.ok(usuariosService.update(id, usuarios));
    }

    @PutMapping("/{id}/rol")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> cambiarRol(@PathVariable Integer id, @RequestParam String rol) {
        Usuarios usuario = usuariosService.findById(id);
        usuario.setRol(rol.toUpperCase());
        usuariosService.update(id, usuario);
        return ResponseEntity.ok(Map.of("message", "Rol actualizado a " + rol));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        usuariosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
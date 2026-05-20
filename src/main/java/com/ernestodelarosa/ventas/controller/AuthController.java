package com.ernestodelarosa.ventas.controller;

import com.ernestodelarosa.ventas.dto.AuthResponse;
import com.ernestodelarosa.ventas.dto.LoginRequest;
import com.ernestodelarosa.ventas.dto.RegisterRequest;
import com.ernestodelarosa.ventas.entity.Usuarios;
import com.ernestodelarosa.ventas.service.UsuariosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuariosService usuariosService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Usuarios usuario = usuariosService.findByUsername(request.getUsername());

        return ResponseEntity.ok(AuthResponse.builder()
                .message("Login exitoso")
                .username(usuario.getUsername())
                .rol(usuario.getRol())
                .build());
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        Usuarios usuario = Usuarios.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .rol("USER")
                .estado(1)
                .build();

        Usuarios usuarioGuardado = usuariosService.save(usuario);

        return ResponseEntity.ok(AuthResponse.builder()
                .message("Usuario registrado exitosamente")
                .username(usuarioGuardado.getUsername())
                .rol(usuarioGuardado.getRol())
                .build());
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(401).body(Map.of("message", "No autenticado"));
        }

        Usuarios usuario = usuariosService.findByUsername(auth.getName());
        return ResponseEntity.ok(Map.of(
                "username", usuario.getUsername(),
                "nombre", usuario.getNombre(),
                "apellido", usuario.getApellido(),
                "rol", usuario.getRol()
        ));
    }
}
package com.ernestodelarosa.ventas.controller;

import com.ernestodelarosa.ventas.service.ClientesService;
import com.ernestodelarosa.ventas.service.ProductosService;
import com.ernestodelarosa.ventas.service.UsuariosService;
import com.ernestodelarosa.ventas.service.VentasService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
@RequiredArgsConstructor
public class WebController {

    private final ProductosService productosService;
    private final ClientesService clientesService;
    private final UsuariosService usuariosService;
    private final VentasService ventasService;

    @GetMapping("/productos")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String listarProductos() {
        return "productos";
    }

    @GetMapping("/clientes")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String listarClientes() {
        return "clientes";
    }

    @GetMapping("/usuarios")
    @PreAuthorize("hasRole('ADMIN')")
    public String listarUsuarios() {
        return "usuarios";
    }

    @GetMapping("/ventas")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String listarVentas() {
        return "ventas";
    }
}
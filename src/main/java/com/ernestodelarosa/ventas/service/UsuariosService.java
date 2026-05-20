package com.ernestodelarosa.ventas.service;

import com.ernestodelarosa.ventas.entity.Usuarios;
import org.springframework.security.core.userdetails.UserDetailsService; // <- Agregar import

import java.util.List;

public interface UsuariosService extends UserDetailsService { // <- Extender aquí
    List<Usuarios> findAll();
    Usuarios findById(Integer codigoUsuario);
    Usuarios findByUsername(String username);
    Usuarios save(Usuarios usuarios);
    Usuarios update(Integer codigoUsuario, Usuarios usuarios);
    void delete(Integer codigoUsuario);
}
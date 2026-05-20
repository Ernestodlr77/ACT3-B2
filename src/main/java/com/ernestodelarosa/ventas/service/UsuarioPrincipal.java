package com.ernestodelarosa.ventas.service;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class UsuarioPrincipal extends User {

    private final Integer codigoUsuario;
    private final String nombre;
    private final String apellido;
    private final String email;

    public UsuarioPrincipal(Integer codigoUsuario, String username, String password,
                            String nombre, String apellido, String email, String rol,
                            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.codigoUsuario = codigoUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
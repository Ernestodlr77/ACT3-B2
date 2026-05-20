package com.ernestodelarosa.ventas.config;

import com.ernestodelarosa.ventas.entity.Usuarios;
import com.ernestodelarosa.ventas.repository.UsuariosRepository;
import com.ernestodelarosa.ventas.service.UsuarioPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuariosRepository usuariosRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuario = usuariosRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        if (usuario.getEstado() == 0) {
            throw new UsernameNotFoundException("Usuario inactivo: " + username);
        }

        String rol = usuario.getRol() != null ? usuario.getRol().toUpperCase() : "USER";
        if (!rol.startsWith("ROLE_")) {
            rol = "ROLE_" + rol;
        }

        return new UsuarioPrincipal(
                usuario.getCodigoUsuario(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                rol,
                Collections.singletonList(new SimpleGrantedAuthority(rol))
        );
    }
}
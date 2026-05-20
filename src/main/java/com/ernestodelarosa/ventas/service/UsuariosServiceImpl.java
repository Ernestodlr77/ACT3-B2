package com.ernestodelarosa.ventas.service;

import com.ernestodelarosa.ventas.entity.Usuarios;
import com.ernestodelarosa.ventas.exception.ResourceNotFoundException;
import com.ernestodelarosa.ventas.repository.UsuariosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsuariosServiceImpl implements UsuariosService {

    private final UsuariosRepository usuariosRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Usuarios> findAll() {
        return usuariosRepository.findAll();
    }

    @Override
    public Usuarios findById(Integer codigoUsuario) {
        return usuariosRepository.findById(codigoUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + codigoUsuario));
    }

    @Override
    public Usuarios findByUsername(String username) {
        return usuariosRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + username));
    }

    @Override
    @Transactional
    public Usuarios save(Usuarios usuarios) {
        if (usuariosRepository.existsByUsername(usuarios.getUsername())) {
            throw new ResourceNotFoundException("El usuario '" + usuarios.getUsername() + "' ya existe");
        }
        usuarios.setPassword(passwordEncoder.encode(usuarios.getPassword()));
        return usuariosRepository.save(usuarios);
    }

    @Override
    @Transactional
    public Usuarios update(Integer codigoUsuario, Usuarios usuariosDetails) {
        Usuarios usuarios = findById(codigoUsuario);

        usuarios.setNombre(usuariosDetails.getNombre());
        usuarios.setApellido(usuariosDetails.getApellido());
        usuarios.setUsername(usuariosDetails.getUsername());
        usuarios.setEmail(usuariosDetails.getEmail());
        usuarios.setRol(usuariosDetails.getRol());
        usuarios.setEstado(usuariosDetails.getEstado());

        if (usuariosDetails.getPassword() != null && !usuariosDetails.getPassword().isEmpty()) {
            usuarios.setPassword(passwordEncoder.encode(usuariosDetails.getPassword()));
        }

        return usuariosRepository.save(usuarios);
    }

    @Override
    @Transactional
    public void delete(Integer codigoUsuario) {
        Usuarios usuarios = findById(codigoUsuario);
        usuariosRepository.delete(usuarios);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
package com.ernestodelarosa.ventas.repository;

import com.ernestodelarosa.ventas.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    Optional<Usuarios> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
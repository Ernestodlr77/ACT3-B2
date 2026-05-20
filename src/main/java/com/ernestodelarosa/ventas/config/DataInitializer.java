package com.ernestodelarosa.ventas.config;

import com.ernestodelarosa.ventas.entity.Usuarios;
import com.ernestodelarosa.ventas.repository.UsuariosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UsuariosRepository usuariosRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initUsers() {
        return args -> {
            if (usuariosRepository.count() == 0) {
                Usuarios user = Usuarios.builder()
                        .username("user")
                        .password(passwordEncoder.encode("123456"))
                        .email("user@test.com")
                        .rol("USER")
                        .estado(1)
                        .nombre("Usuario")
                        .apellido("Normal")
                        .build();

                Usuarios admin = Usuarios.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .email("admin@test.com")
                        .rol("ADMIN")
                        .estado(1)
                        .nombre("Sistema")
                        .apellido("Jefe")
                        .build();

                usuariosRepository.save(user);
                usuariosRepository.save(admin);

                System.out.println("Usuarios de prueba creados: user/123456 y admin/admin123");
            }
        };
    }
}
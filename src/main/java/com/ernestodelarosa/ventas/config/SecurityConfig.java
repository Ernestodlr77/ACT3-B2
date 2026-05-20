package com.ernestodelarosa.ventas.config;

import org.springframework.beans.factory.annotation.Qualifier; // 👈 Asegúrate de importar esto
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
// ❌ Quitamos @RequiredArgsConstructor para controlar la inyección manualmente
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    // Inyectamos explícitamente el "customUserDetailsService" usando @Qualifier
    public SecurityConfig(@Qualifier("customUserDetailsService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        // 1. Recursos Públicos y Autenticación
                        .requestMatchers("/", "/index", "/login", "/registro", "/access-denied").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/favicon.ico").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()

                        // 2. Control de Vistas Web (Hojas HTML del Sidebar)
                        .requestMatchers("/home").authenticated()
                        .requestMatchers("/web/productos", "/web/clientes", "/web/ventas").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/web/usuarios").hasRole("ADMIN")

                        // 3. Control de APIs de Datos (REST Endpoints)
                        .requestMatchers("/api/productos/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/clientes/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/ventas/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/usuarios/**").hasRole("ADMIN")

                        // Cualquier otra petición requiere estar logueado
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform-login")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/access-denied")
                );

        return http.build();
    }
}
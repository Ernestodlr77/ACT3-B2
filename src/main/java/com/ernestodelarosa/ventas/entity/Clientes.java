package com.ernestodelarosa.ventas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clientes {

    @Id
    @Column(name = "dpi_cliente", nullable = false, unique = true)
    private Integer dpiCliente;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre debe tener máximo 50 caracteres")
    @Column(name = "nombre_cliente", nullable = false, length = 50)
    private String nombreCliente;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50, message = "El apellido debe tener máximo 50 caracteres")
    @Column(name = "apellido_cliente", nullable = false, length = 50)
    private String apellidoCliente;

    @Size(max = 100, message = "La dirección debe tener máximo 100 caracteres")
    @Column(name = "direccion", length = 100)
    private String direccion;

    @Column(name = "estado")
    @Builder.Default
    private Integer estado = 1;
}
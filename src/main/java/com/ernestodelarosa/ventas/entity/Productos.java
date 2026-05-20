package com.ernestodelarosa.ventas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_producto")
    private Integer codigoProducto;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 60, message = "El nombre debe tener máximo 60 caracteres")
    @Column(name = "nombre_producto", nullable = false, length = 60)
    private String nombreProducto;

    @NotBlank(message = "La marca es obligatoria")
    @Size(max = 30)
    @Column(name = "marca", nullable = false, length = 30)
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    @Size(max = 40)
    @Column(name = "modelo", nullable = false, length = 40)
    private String modelo;

    @Size(max = 10)
    @Column(name = "ram", length = 10)
    private String ram;

    @Size(max = 10)
    @Column(name = "almacenamiento", length = 10)
    private String almacenamiento;

    @Size(max = 20)
    @Column(name = "color", length = 20)
    private String color;

    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "stock")
    @Builder.Default
    private Integer stock = 0;

    @Column(name = "estado")
    @Builder.Default
    private Integer estado = 1;
}
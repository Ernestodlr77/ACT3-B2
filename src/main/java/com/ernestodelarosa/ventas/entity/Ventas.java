package com.ernestodelarosa.ventas.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_venta")
    private Integer codigoVenta;

    @NotNull(message = "La fecha de venta es obligatoria")
    @Column(name = "fecha_venta", nullable = false)
    private LocalDate fechaVenta;

    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "estado")
    @Builder.Default
    private Integer estado = 1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientes_dpi_cliente", nullable = false)
    private Clientes cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuarios_codigo_usuario", nullable = false)
    private Usuarios usuario;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @JsonIgnoreProperties("venta")
    private List<DetalleVenta> detalleVentas = new ArrayList<>();

    public void addDetalleVenta(DetalleVenta detalle) {
        detalleVentas.add(objetoYRelacion(detalle));
    }

    private DetalleVenta objetoYRelacion(DetalleVenta detalle) {
        detalle.setVenta(this);
        return detalle;
    }

    public void removeDetalleVenta(DetalleVenta detalle) {
        detalleVentas.remove(detalle);
        detalle.setVenta(null);
    }
}
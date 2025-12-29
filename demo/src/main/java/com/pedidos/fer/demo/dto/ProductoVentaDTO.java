package com.pedidos.fer.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoVentaDTO {
    private Long id;
    private String nombre;
    private double precio;
    private int cantidad;
    private double subtotal;
}

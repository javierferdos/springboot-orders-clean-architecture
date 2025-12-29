package com.pedidos.fer.demo.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoRequestDTO {
    private String nombre;
    private Double precio;
    private int Stock;
}

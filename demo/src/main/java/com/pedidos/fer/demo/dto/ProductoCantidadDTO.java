package com.pedidos.fer.demo.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoCantidadDTO {
    private Long id;
    private Integer cantidad;
}

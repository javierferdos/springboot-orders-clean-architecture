package com.pedidos.fer.demo.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoRequestDTO {
    private List<ProductoCantidadDTO> productos;
}

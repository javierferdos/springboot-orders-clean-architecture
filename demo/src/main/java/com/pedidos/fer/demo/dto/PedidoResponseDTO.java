package com.pedidos.fer.demo.dto;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoResponseDTO {
    private Long id;
    private LocalDateTime fecha;
    private List<ProductoVentaDTO> productos;
    private double total;

}

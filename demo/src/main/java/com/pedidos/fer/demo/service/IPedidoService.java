package com.pedidos.fer.demo.service;


import com.pedidos.fer.demo.dto.PedidoRequestDTO;
import com.pedidos.fer.demo.dto.PedidoResponseDTO;
import com.pedidos.fer.demo.dto.ProductoRequestDTO;
import com.pedidos.fer.demo.entity.Pedido;

import java.util.List;

public interface IPedidoService {
    List<PedidoResponseDTO> obtenerVentas();
    PedidoResponseDTO getPedidobyId(Long id);
    PedidoResponseDTO createPedido(PedidoRequestDTO pedidoRequestDTO);
}

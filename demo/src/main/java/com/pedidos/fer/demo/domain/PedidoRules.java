package com.pedidos.fer.demo.domain;

import com.pedidos.fer.demo.dto.ProductoRequestDTO;
import com.pedidos.fer.demo.exception.BadRequestException;

public class PedidoRules {

    public static void validarActualizacion(ProductoRequestDTO request) {
        if (request == null) {
            throw new BadRequestException("Request nulo");
        }
        if (request.getStock() < 0) {
            throw new BadRequestException("Stock inválido");
        }
        if (request.getPrecio() < 0) {
            throw new BadRequestException("Precio inválido");
        }
    }

    public static void validarStock(int cantidad, int stock) {
        if (cantidad <= 0) {
            throw new BadRequestException("Cantidad inválida");
        }
        if (cantidad > stock) {
            throw new BadRequestException("Stock insuficiente");
        }
    }
}

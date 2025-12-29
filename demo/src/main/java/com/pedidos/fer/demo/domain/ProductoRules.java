package com.pedidos.fer.demo.domain;

import com.pedidos.fer.demo.dto.ProductoRequestDTO;
import com.pedidos.fer.demo.exception.BadRequestException;

public class ProductoRules {
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


}

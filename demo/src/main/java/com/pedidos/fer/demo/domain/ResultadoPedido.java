package com.pedidos.fer.demo.domain;

import com.pedidos.fer.demo.entity.PedidoDetalle;

import java.util.List;

public record ResultadoPedido(List<PedidoDetalle> detalles, double totalBruto, double totalFinal) {

}

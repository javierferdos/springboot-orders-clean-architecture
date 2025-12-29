package com.pedidos.fer.demo.mapper;

import com.pedidos.fer.demo.dto.PedidoResponseDTO;
import com.pedidos.fer.demo.dto.ProductoRequestDTO;
import com.pedidos.fer.demo.dto.ProductoResponseDTO;
import com.pedidos.fer.demo.dto.ProductoVentaDTO;
import com.pedidos.fer.demo.entity.Pedido;
import com.pedidos.fer.demo.entity.Producto;

import java.util.List;

public class Mapper {

    public static Producto toProductoEntity(ProductoRequestDTO dto) {
        if (dto == null) return null;

        return Producto.builder()
                .nombre(dto.getNombre())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .build();
    }

    public static ProductoResponseDTO toProductoResponseDTO(Producto producto) {
        if (producto == null) return null;

        return ProductoResponseDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .Stock(producto.getStock())
                .build();
    }

    public static PedidoResponseDTO toPedidoResponseDTO(Pedido pedido) {

        if (pedido == null) return null;
        List<ProductoVentaDTO> productoVenta = pedido.getDetalles().stream()
                .map(d -> ProductoVentaDTO.builder()
                        .id(d.getProducto().getId())
                        .nombre(d.getProducto().getNombre())
                        .precio(d.getProducto().getPrecio())
                        .subtotal(d.getSubTotal())
                        .cantidad(d.getCantidad())
                        .build()
                ).toList();

        return PedidoResponseDTO.builder()
                .id(pedido.getId())
                .fecha(pedido.getFecha())
                .productos(productoVenta)
                .total(pedido.getTotal())
                .build();
    }

}

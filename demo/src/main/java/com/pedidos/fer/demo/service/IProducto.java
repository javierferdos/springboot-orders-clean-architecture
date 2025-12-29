package com.pedidos.fer.demo.service;

import com.pedidos.fer.demo.dto.ProductoRequestDTO;
import com.pedidos.fer.demo.dto.ProductoResponseDTO;
import com.pedidos.fer.demo.entity.Producto;

import java.util.List;

public interface IProducto {
    List<ProductoResponseDTO> obtenerProductos();
    ProductoResponseDTO findById(Long id);
    ProductoResponseDTO updateProducto(ProductoRequestDTO productoRequestDTO, Long id);
    void deleteProducto(Long id);
    ProductoResponseDTO createProducto(ProductoRequestDTO productoRequestDTO);
}

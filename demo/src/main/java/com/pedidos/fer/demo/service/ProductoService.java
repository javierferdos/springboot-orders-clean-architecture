package com.pedidos.fer.demo.service;

import com.pedidos.fer.demo.domain.ProductoRules;
import com.pedidos.fer.demo.dto.ProductoRequestDTO;
import com.pedidos.fer.demo.dto.ProductoResponseDTO;
import com.pedidos.fer.demo.entity.Producto;
import com.pedidos.fer.demo.exception.ResourceNotFoundException;
import com.pedidos.fer.demo.mapper.Mapper;
import com.pedidos.fer.demo.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService implements IProducto{

    private final ProductoRepository productoRepository;

    @Override
    public List<ProductoResponseDTO> obtenerProductos() {
        return productoRepository.findAll().stream().map(Mapper::toProductoResponseDTO).toList();
    }


    @Override
    public ProductoResponseDTO findById(Long id) {
        return productoRepository.findById(id).map(Mapper::toProductoResponseDTO).orElseThrow(
                () -> new ResourceNotFoundException("No se encontro el producto con el id: " + id)
        );
    }

    @Override
    @Transactional
    public void deleteProducto(Long id) {
        productoRepository.findById(id).map(Mapper::toProductoResponseDTO).orElseThrow(
                ()-> new ResourceNotFoundException("No se encontro el producto con el id: " + id)
        );
        productoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ProductoResponseDTO createProducto(ProductoRequestDTO productoRequestDTO) {
        ProductoRules.validarActualizacion(productoRequestDTO);
        Producto producto = crearProductoBase(productoRequestDTO);
        Producto guardado = productoRepository.save(producto);
        return Mapper.toProductoResponseDTO(guardado);
    }
    /*Validar producto base crear*/
    public Producto crearProductoBase(ProductoRequestDTO request) {
        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setStock(request.getStock());
        producto.setPrecio(request.getPrecio());
        return producto;
    }

    @Override
    @Transactional
    public ProductoResponseDTO updateProducto(ProductoRequestDTO productoRequestDTO, Long id) {
        ProductoRules.validarActualizacion(productoRequestDTO);

        Producto producto = validarUpdate(productoRequestDTO, id);
        Producto guardado = productoRepository.save(producto);
        return Mapper.toProductoResponseDTO(guardado);
    }
    /*validar */
    public Producto validarUpdate(ProductoRequestDTO request, Long id) {

        Producto producto = obtenerProducto(id);

        if (request.getNombre() != null) {
            producto.setNombre(request.getNombre());
        }
        if (request.getStock() >= 0) {
            producto.setStock(request.getStock());
        }
        if (request.getPrecio() >= 0) {
            producto.setPrecio(request.getPrecio());
        }
        return producto;
    }
    private Producto obtenerProducto(Long id){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("producto con id: "+ id + " no encontrado"));
        return producto;
    }


}

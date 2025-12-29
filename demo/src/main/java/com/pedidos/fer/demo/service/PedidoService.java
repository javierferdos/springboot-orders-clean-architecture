package com.pedidos.fer.demo.service;

import com.pedidos.fer.demo.domain.PedidoRules;
import com.pedidos.fer.demo.domain.ResultadoPedido;
import com.pedidos.fer.demo.dto.PedidoRequestDTO;
import com.pedidos.fer.demo.dto.PedidoResponseDTO;
import com.pedidos.fer.demo.dto.ProductoCantidadDTO;
import com.pedidos.fer.demo.dto.ProductoRequestDTO;
import com.pedidos.fer.demo.entity.Pedido;
import com.pedidos.fer.demo.entity.PedidoDetalle;
import com.pedidos.fer.demo.entity.Producto;
import com.pedidos.fer.demo.exception.BadRequestException;
import com.pedidos.fer.demo.exception.ResourceNotFoundException;
import com.pedidos.fer.demo.mapper.Mapper;
import com.pedidos.fer.demo.repository.PedidoRepository;
import com.pedidos.fer.demo.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService implements IPedidoService{

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    @Override
    public List<PedidoResponseDTO> obtenerVentas() {
        return pedidoRepository.findAll().stream().map(Mapper::toPedidoResponseDTO).toList();
    }


    @Override
    public PedidoResponseDTO getPedidobyId(Long id) {
        return pedidoRepository.findById(id).map(Mapper::toPedidoResponseDTO).orElseThrow(
                () -> new ResourceNotFoundException("Producto no encontrado")
        );
    }

    @Transactional
    @Override
    public PedidoResponseDTO createPedido(PedidoRequestDTO request) {
        validarRequest(request);
        Pedido pedido = inicializarVenta();
        ResultadoPedido resultado = calcularResultadoVenta(request.getProductos(), pedido);
        pedido.setDetalles(resultado.detalles());
        pedido.setTotal(resultado.totalFinal());

        Pedido guardado = pedidoRepository.save(pedido);
        return Mapper.toPedidoResponseDTO(guardado);
    }

    private void validarRequest(PedidoRequestDTO request) {
        if (request == null || request.getProductos() == null || request.getProductos().isEmpty()) {
            throw new BadRequestException("La venta debe contener al menos un producto");
        }
    }
    private Pedido inicializarVenta() {
        Pedido venta = new Pedido();
        venta.setFecha(LocalDateTime.now());
        return venta;
    }
    private ResultadoPedido calcularResultadoVenta(List<ProductoCantidadDTO> items, Pedido pedido) {

        List<PedidoDetalle> detalles = new ArrayList<>();
        double totalBruto = 0;

        for (ProductoCantidadDTO item : items) {

            Producto producto = obtenerProducto(item.getId());

            PedidoRules.validarStock(item.getCantidad(), producto.getStock());

            producto.descontarStock(item.getCantidad());

            PedidoDetalle detalle = new PedidoDetalle();
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalles.add(detalle);

            totalBruto += detalle.getSubTotal();
        }
        double totalFinal = totalBruto;

        return new ResultadoPedido(detalles, totalBruto, totalFinal);
    }

    private Producto obtenerProducto(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Producto no existe"));
    }
}

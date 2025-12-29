package com.pedidos.fer.demo.controller;

import com.pedidos.fer.demo.dto.PedidoRequestDTO;
import com.pedidos.fer.demo.dto.PedidoResponseDTO;
import com.pedidos.fer.demo.service.IPedidoService;
import com.pedidos.fer.demo.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ventas")public class PedidoController {

    private final IPedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> obtenerPedidos() {
        return ResponseEntity.ok(pedidoService.obtenerVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> obtenerPedidoById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.getPedidobyId(id));
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> crearPedido(@RequestBody PedidoRequestDTO pedidoRequestDTO) {
        PedidoResponseDTO creado = pedidoService.createPedido(pedidoRequestDTO);
        // Devuelves 201 Created y la URI del nuevo recurso
        return ResponseEntity
                .created(URI.create("/api/pedidos/" + creado.getId()))
                .body(creado);
    }
}

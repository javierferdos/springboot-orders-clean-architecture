package com.pedidos.fer.demo.repository;

import com.pedidos.fer.demo.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
    
}

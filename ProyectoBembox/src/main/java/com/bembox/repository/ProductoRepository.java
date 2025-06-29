package com.bembox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bembox.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Long>{

}

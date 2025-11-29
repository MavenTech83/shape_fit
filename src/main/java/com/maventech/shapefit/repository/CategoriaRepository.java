package com.maventech.shapefit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maventech.shapefit.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByNomeContainingIgnoreCase(String nome);

}

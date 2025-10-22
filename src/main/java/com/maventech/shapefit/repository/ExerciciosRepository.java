package com.maventech.shapefit.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maventech.shapefit.model.Exercicios;


@Repository
public interface ExerciciosRepository extends JpaRepository<Exercicios, Long> {

    // Método extra para buscar por nome, sem diferenciar maiúscula e minúscula//
    List<Exercicios> findAllByNomeContainingIgnoreCase(String nome);
}

package com.maventech.shapefit.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.maventech.shapefit.model.Exercicios;
import com.maventech.shapefit.repository.ExerciciosRepository;
import jakarta.validation.Valid;

		@RestController
		@RequestMapping("/exercicios")
		@CrossOrigin(origins = "*", allowedHeaders = "*")
		public class ExerciciosController {

			@Autowired
			private ExerciciosRepository exerciciosRepository;

			// CREATE
			@PostMapping
			public ResponseEntity<Exercicios> post(@Valid @RequestBody Exercicios exercicio) {
			return ResponseEntity.status(HttpStatus.CREATED)
                .body(exerciciosRepository.save(exercicio));
    }

			// READ 
			@GetMapping
			public ResponseEntity<List<Exercicios>> getAll() {
			return ResponseEntity.ok(exerciciosRepository.findAll());
    }

    		// READ - buscar por ID
			@GetMapping("/{id}")
			public ResponseEntity<Exercicios> getById(@PathVariable Long id) {
			return exerciciosRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

     		// READ - buscar por nome
			@GetMapping("/nome/{nome}")
			public ResponseEntity<List<Exercicios>> getByNome(@PathVariable String nome) {
			return ResponseEntity.ok(exerciciosRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    		// UPDATE
			@PutMapping
			public ResponseEntity<Exercicios> put(@Valid @RequestBody Exercicios exercicio) {
			return exerciciosRepository.findById(exercicio.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                        .body(exerciciosRepository.save(exercicio)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    	  // DELETE
			@DeleteMapping("/{id}")
			public ResponseEntity<?> delete(@PathVariable Long id) {
				return exerciciosRepository.findById(id)
                .map(resposta -> {
                    exerciciosRepository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}

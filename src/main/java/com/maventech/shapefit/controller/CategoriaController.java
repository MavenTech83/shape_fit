package com.maventech.shapefit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.maventech.shapefit.model.Categoria;
import com.maventech.shapefit.repository.CategoriaRepository;

import jakarta.validation.Valid;

@RestController // indica que a classe é um controlador REST
@RequestMapping("/categorias") // mapeamento da URL para acessar os endpoints deste controlador
@CrossOrigin(origins = "*", allowedHeaders = "*") // libera o acesso para qualquer origem 
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	
	// LISTAR TODAS AS CATEGORIAS
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll() {
		return ResponseEntity.ok(categoriaRepository.findAll());
		// SELECT * FROM tb_categorias ,ou seja, traz todas as categorias

	}
	
	// LISTAR CATEGORIA POR ID
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id) {
		return categoriaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta)) 
				// se encontrar o id, retorna o status OK													
				.orElse(ResponseEntity.notFound().build());
		        // se não encontrar o id, retorna o status 404 (NOT FOUND)
	
	}
	
	// LISTAR CATEGORIAS POR NOME 
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Categoria>> getByNome(@PathVariable String nome) {
		List<Categoria> lista = categoriaRepository.findAllByNomeContainingIgnoreCase(nome);
        if (lista.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(lista);
}
	
	// CRIAR
	@PostMapping
	public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria) {
	    categoria.setId(null); // garante que o ID seja nulo para criar um novo registro
	    return ResponseEntity
	            .status(HttpStatus.CREATED)
	            .body(categoriaRepository.save(categoria));
	}
	
	// ATUALIZAR
	@PutMapping
	public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria) {
		
		if (categoria.getId() == null)
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // se näao for enviado o id, retorna o status 400 (BAD REQUEST)
		
		return categoriaRepository.findById(categoria.getId()) // verifica se o id existe no banco de dados
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
						.body(categoriaRepository.save(categoria))) // se encontrar o id, atualiza a categoria e retorna o status OK
				.orElse(ResponseEntity.notFound().build()); // se não encontrar o id, retorna o status 404 (NOT FOUND)
	
}
	
	// DELETAR
	@ResponseStatus(HttpStatus.NO_CONTENT) // se a deleção for bem sucedida, retorna o status 204 (NO CONTENT)
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id) {
	    
	    Optional<Categoria> categoria = categoriaRepository.findById(id); 
	    // verifica se o id existe no banco de dados e senão existir, retorna um Optional vazio
	    
		if (categoria.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
		// se não encontrar o id, retorna o status 404 (NOT FOUND)
		
		categoriaRepository.deleteById(id); // se encontrar o id, deleta a categoria 
																		
		}	

}

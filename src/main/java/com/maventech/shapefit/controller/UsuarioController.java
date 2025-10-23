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

import com.maventech.shapefit.model.Usuario;
import com.maventech.shapefit.repository.UsuarioRepository;
import com.maventech.shapefit.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired // Injeta o IMC
    private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll() {
		return ResponseEntity.ok(usuarioRepository.findAll());

	}
	
	@GetMapping("/{id}")  //variável de caminho, variável esta no URL
	public ResponseEntity<Usuario> getById(@PathVariable Long id) {
		return usuarioRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());

		// SELECT * FROM tb_postagens WHERE id = ?;
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Usuario>> gfindAllByNomeContainingIgnoreCase(@PathVariable String nome) {
		return ResponseEntity.ok(usuarioRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<List<Usuario>> getAllByNome(@PathVariable String email) {
		return ResponseEntity.ok(usuarioRepository.findAllByEmailContainingIgnoreCase(email));
	}

	@PostMapping //insere dados, cadastra
	public ResponseEntity<Usuario> post(@Valid @RequestBody Usuario usuario) {
			usuario.setId(null);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
	}
	
	@PutMapping  //atualiza dados
	public ResponseEntity<Usuario> put(@Valid @RequestBody Usuario usuario) {
		
		return usuarioRepository.findById(usuario.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
						.body(usuarioRepository.save(usuario)))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PutMapping("/imc/{id}") 
	public ResponseEntity<Usuario> calcularImc(@PathVariable Long id) {
	   	    return usuarioService.calcularImc(id)
	            .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
	            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if(usuario.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		usuarioRepository.deleteById(id);
	}
}
	
	


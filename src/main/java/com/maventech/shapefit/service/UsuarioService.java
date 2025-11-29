package com.maventech.shapefit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maventech.shapefit.model.Usuario;
import com.maventech.shapefit.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> calcularImc(Long id) {
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
		
		if (usuarioExistente.isEmpty()) {
			return Optional.empty();
		}
		
		Usuario usuario = usuarioExistente.get();
		Double peso = usuario.getPeso();
		Double altura = usuario.getAltura();
		
		if (peso != null && altura != null && altura > 0) {
			Double imcCalculado = peso / (altura * altura);
			imcCalculado = Math.round(imcCalculado * 100.0) / 100.0;
			
			usuario.setImc(imcCalculado);
			
			return Optional.of(usuarioRepository.save(usuario));
			
		}
		
		return usuarioExistente;
		
	}
	
}

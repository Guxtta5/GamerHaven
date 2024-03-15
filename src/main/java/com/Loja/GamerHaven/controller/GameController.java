package com.Loja.GamerHaven.controller;


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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.Loja.GamerHaven.model.Game;
import com.Loja.GamerHaven.repository.CategoriaRepository;
import com.Loja.GamerHaven.repository.GameRepository;

import jakarta.validation.Valid;


import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GameController {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Game>> getAll(){
		return ResponseEntity.ok(gameRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Game> getById(@PathVariable Long id){
		return gameRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nomeDoGame/{nomeDoGame}")
	public ResponseEntity<List<Game>> getByTitulo(@PathVariable String nomeDoGame) {
		return ResponseEntity.ok(gameRepository
				.findAllByNomeDoGameContainingIgnoreCase(nomeDoGame));
	}
	
	@GetMapping("/desenvolvedora/{desenvolvedora}")
	public ResponseEntity<List<Game>> getBydesenvolvedora(@PathVariable String desenvolvedora){
		return ResponseEntity.ok(gameRepository
				.findAllByDesenvolvedoraContainingIgnoreCase(desenvolvedora));
	}
	
	@GetMapping("/publicadora/{publicadora}")
	public ResponseEntity<List<Game>> getBypublicadora (@PathVariable String publicadora){
		return ResponseEntity.ok(gameRepository
				.findAllByPublicadoraContainingIgnoreCase(publicadora));
	}
	
	@PostMapping
	public ResponseEntity<Game> post (@Valid @RequestBody Game game){
		if(gameRepository.existsById(game.getCategoria().getId()))
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(gameRepository.save(game));
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Essa Categoria não Existe!", null);
	}
	
	@PutMapping
	public ResponseEntity<Game> put (@Valid @RequestBody Game game){
		if(gameRepository.existsById(game.getId())) {
			if(categoriaRepository.existsById(game.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(gameRepository.save(game));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Essa Categoria não Existe!", null);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Game> game = gameRepository.findById(id);
		
		if(game.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		gameRepository.deleteById(id);
	}
}

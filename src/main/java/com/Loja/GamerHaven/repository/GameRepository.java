package com.Loja.GamerHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.Loja.GamerHaven.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
	
	public List<Game> findAllByNomeDoGameContainingIgnoreCase(@Param("nomeDoGame") String nomeDoGame);
	
	public List<Game> findAllByDesenvolvedoraContainingIgnoreCase(@Param("desenvolvedora") String desenvolvedora);
	
	public List<Game> findAllByPublicadoraContainingIgnoreCase(@Param("publicadora") String publicadora);
	
}

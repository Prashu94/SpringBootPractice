package com.infosys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.dto.PlayerDTO;
import com.infosys.entity.Player;
import com.infosys.exception.InfyPlayerException;
import com.infosys.repository.PlayerRepository;

@Service(value = "playerService")
@Transactional
public class PlayerServiceImpl implements PlayerService{

	@Autowired
	private PlayerRepository playerRepository;
	
	@Override
	public List<PlayerDTO> getFirstSevenPlayers(int pageNo, int pageSize) throws InfyPlayerException {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Player> page = playerRepository.findAll(pageable);
		
		
		if(page.isEmpty()) {
			throw new InfyPlayerException("Service.NO_PLAYERS_IN_THIS_PAGE");
		}
		
		List<Player> playerList = page.getContent();
		
		List<PlayerDTO> playerDTOs;
		
		playerDTOs = playerList
				.stream()
				.map(player -> new PlayerDTO(player.getPlayerId(), player.getPlayerName(), player.getRanking(), player.getBattingStyle(), 
						player.getBowlingStyle() , player.getDebutDate(), player.getCountry()))
				.collect(Collectors.toList());
		
		
		return playerDTOs;
	}

	@Override
	public List<PlayerDTO> getAllPlayersByDebutDateAfter(String debutDate, int i, int j) throws InfyPlayerException {
		Pageable pageable = PageRequest.of(i, j);
		List<Player> players = playerRepository.findByDebutDateAfter(debutDate, pageable);
		
		if(players.isEmpty()) {
			throw new InfyPlayerException("Service.NO_PLAYERS_IN_THIS_PAGE");
		}
		
		List<PlayerDTO> playerDTOs;
		
		playerDTOs = players.stream()
				.map(player -> new PlayerDTO(player.getPlayerId(), player.getPlayerName(), player.getRanking(), player.getBattingStyle(), 
						player.getBowlingStyle() , player.getDebutDate(), player.getCountry()))
				.collect(Collectors.toList());
		return playerDTOs;
	}
	
	

	@Override
	public List<PlayerDTO> getAllPlayersSortedByRanking(Sort sort) throws InfyPlayerException {
		Iterable<Player> players = playerRepository.findAll(sort);
		List<PlayerDTO> playerDTOs = new ArrayList<>();
		
		
		
		players.forEach(player -> {
			PlayerDTO playerDTO = new PlayerDTO(player.getPlayerId(), player.getPlayerName(), player.getRanking(), player.getBattingStyle(), 
					player.getBowlingStyle() , player.getDebutDate(), player.getCountry());
			playerDTOs.add(playerDTO);
		});
		
		if(playerDTOs.isEmpty()) {
			throw new InfyPlayerException("Service.NO_PLAYERS_IN_THIS_PAGE");
		}
		
		return playerDTOs;
	}

	@Override
	public List<PlayerDTO> getAllPlayersOfCountrySortedByRanking(String country, Sort sort) throws InfyPlayerException {
		Pageable pageable = PageRequest.of(0, 5, sort);
		List<Player> players = playerRepository.findByCountry(country, pageable);
		
		if(players.isEmpty()) {
			throw new InfyPlayerException("Service.NO_PLAYERS_IN_THIS_PAGE");
		}
		
		return players.stream()
				.map(player -> new PlayerDTO(player.getPlayerId(), player.getPlayerName(), player.getRanking(), player.getBattingStyle(), 
					player.getBowlingStyle() , player.getDebutDate(), player.getCountry()))
				.collect(Collectors.toList());
	}

}

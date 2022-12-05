package com.infosys.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.infosys.entity.Player;

public interface PlayerRepository extends PagingAndSortingRepository<Player, Integer> {
	
	public List<Player> findByDebutDateAfter(String debutDate, Pageable pageable);
	public List<Player> findByCountry(String country, Pageable pageable);
}

package com.prospring5.dao;

import java.util.List;

import com.prospring5.entity.Instrument;
import com.prospring5.entity.Singer;

public interface SingerDao {
	
	List<Singer> findAll();
	
	List<Singer> findAllWithAlbum();
	
	Singer findById(Long id);
	
	Singer save(Singer singer);
	
	void delete(Singer singer);
	
	Instrument save(Instrument instrument);
}

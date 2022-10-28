package com.prospring5.dao;

import java.util.List;

import com.prospring5.entities.Singer;

public interface SingerDao {
	List<Singer> findAll();
	
	List<Singer> findByFirstName(String firstName);
	
	String findNameById(Long id);
	
	String findLastNameById(Long id);
	
	void insert(Singer singer);
	
	void update(Singer singer);
	
	void delete(Long singerId);
	
	List<Singer> findAllWithAlbums();
	
	void insertWithAlbums(Singer singer);
	
	public Singer findNameByIdV1(Long id);
	
}

package com.infosys.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.dto.MovieDTO;
import com.infosys.repository.MovieRepository;

@Service(value = "movieService")
@Transactional
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public MovieDTO getMovieByName(String movieName) {
		return movieRepository.getMovieByName(movieName);
	}

	@Override
	public List<MovieDTO> getMoviesByImdbRating(Double fromRating, Double toRating) {
		return movieRepository.getMoviesByImdbRating(fromRating, toRating);
	}

	@Override
	public List<Object[]> getMoviesByNameAndYear(String directorName) {
		return movieRepository.getMoviesNameAndYear(directorName);
	}

}

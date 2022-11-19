package com.infosys.service;

import java.util.List;

import com.infosys.dto.MovieDTO;

public interface MovieService {
	public MovieDTO getMovieByName(String movieName);
	public List<MovieDTO> getMoviesByImdbRating(Double fromRating, Double toRating);
	public List<Object[]> getMoviesByNameAndYear(String directorName);
}

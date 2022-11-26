package com.infosys.service;

import java.util.List;

import com.infosys.dto.MovieDTO;
import com.infosys.exception.InfyMovieException;

public interface MovieService {
	public MovieDTO getMovieByName(String movieName) throws InfyMovieException;
	public List<MovieDTO> getMoviesByImdbRating(Double fromRating, Double toRating) throws InfyMovieException;
	public List<Object[]> getMoviesByNameAndYear(String directorName) throws InfyMovieException;
	public List<MovieDTO> getMovieByRating(Double fromRating) throws InfyMovieException;
	public List<MovieDTO> getHighestRatedMovie(String directorName) throws InfyMovieException;
	public Double getAverageDirectorRating(String directorName) throws InfyMovieException;
	public Long getNumberOfMoviesReleased(Integer fromYear, Integer toYear) throws InfyMovieException;
}

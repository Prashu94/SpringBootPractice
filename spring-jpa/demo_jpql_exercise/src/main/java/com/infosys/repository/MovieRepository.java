package com.infosys.repository;

import java.util.List;

import com.infosys.dto.MovieDTO;

public interface MovieRepository {
	
	public List<MovieDTO> getMoviesByImdbRating(Double fromRating, Double toRating);
	public MovieDTO getMovieByName(String movieName);
	public List<Object[]> getMoviesNameAndYear(String directorName);
	public List<MovieDTO> getMovieByRating(Double fromRating);
	public List<MovieDTO> getHighestRatedMovie(String directorName);
	public Double getAverageDirectorRating(String directorName);
	public Long getNumberOfMoviesReleased(Integer fromYear, Integer toYear);
}

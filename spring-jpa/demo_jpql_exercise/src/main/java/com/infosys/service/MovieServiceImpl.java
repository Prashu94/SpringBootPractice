package com.infosys.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.dto.MovieDTO;
import com.infosys.exception.InfyMovieException;
import com.infosys.repository.MovieRepository;

@Service(value = "movieService")
@Transactional
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public MovieDTO getMovieByName(String movieName) throws InfyMovieException{
		MovieDTO movieDTO = movieRepository.getMovieByName(movieName);
		if(movieDTO == null) {
			throw new InfyMovieException("Service.NO_MOVIE_FOUND");
		}
		return movieRepository.getMovieByName(movieName);
	}

	@Override
	public List<MovieDTO> getMoviesByImdbRating(Double fromRating, Double toRating) throws InfyMovieException{
		List<MovieDTO> movieDTOs =movieRepository.getMoviesByImdbRating(fromRating, toRating);
		if(movieDTOs.isEmpty()) {
			throw new InfyMovieException("Service.NO_MOVIES_FOUND");
		}
		return movieDTOs;
	}

	@Override
	public List<Object[]> getMoviesByNameAndYear(String directorName) throws InfyMovieException{
		List<Object[]> movieDTOs = movieRepository.getMoviesNameAndYear(directorName);
		if(movieDTOs.isEmpty()) {
			throw new InfyMovieException("Service.NO_MOVIES_FOUND");
		}
		return movieDTOs;
	}

	@Override
	public List<MovieDTO> getMovieByRating(Double fromRating) throws InfyMovieException{
		List<MovieDTO> movieDTOs = movieRepository.getMovieByRating(fromRating);
		if(movieDTOs.isEmpty()) {
			throw new InfyMovieException("Service.NO_MOVIES_FOUND");
		}
		return movieDTOs;
	}

	@Override
	public List<MovieDTO> getHighestRatedMovie(String directorName) throws InfyMovieException{
		List<MovieDTO> movieDTOs = movieRepository.getHighestRatedMovie(directorName);
		if(movieDTOs.isEmpty()) {
			throw new InfyMovieException("Service.NO_MOVIES_FOUND");
		}
		return movieDTOs;
	}

	@Override
	public Double getAverageDirectorRating(String directorName) throws InfyMovieException{
		Double averageRating = movieRepository.getAverageDirectorRating(directorName);
		if(averageRating==null) {
			return 0.0d;
		}
		return averageRating;
	}

	@Override
	public Long getNumberOfMoviesReleased(Integer fromYear, Integer toYear) throws InfyMovieException{
		Long numberOfMoviesReleased = movieRepository.getNumberOfMoviesReleased(fromYear, toYear);
		if(numberOfMoviesReleased==null) {
			return 0L;
		}
		return numberOfMoviesReleased;
	}

}

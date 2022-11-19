package com.infosys.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.infosys.dto.MovieDTO;
import com.infosys.entity.Movie;

@Repository(value = "movieRepository")
public class MovieRepositoryImpl implements MovieRepository{

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<MovieDTO> getMoviesByImdbRating(Double fromRating, Double toRating) {
		/*
		 * - This method fetches the details of movies which has rating between fromRating
		 * and toRating.
		 * - If no movies are found, then return an empty list
		 * Else, return the list of movie details*/
		List<MovieDTO> movieDTOs = null;
		String stringQuery = "SELECT m from Movie m where m.imdbRating between ?1 and ?2 and m.imdbRating is not null";
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter(1, fromRating);
		query.setParameter(2, toRating);
		List<Movie> movies = query.getResultList();
		movieDTOs = new ArrayList<>();
		for(Movie movie: movies) {
			MovieDTO movieDTO = new MovieDTO();
			movieDTO.setDirectorName(movie.getDirectorName());
			movieDTO.setImdbRating(movie.getImdbRating());
			movieDTO.setMovieId(movie.getMovieId());
			movieDTO.setMovieName(movie.getMovieName());
			movieDTO.setYear(movie.getYear());
			movieDTOs.add(movieDTO);
		}
		return movieDTOs;
	}

	@Override
	public MovieDTO getMovieByName(String movieName) {
		String stringQuery = "SELECT m from Movie m where m.movieName = ?1";
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter(1, movieName);
		Movie movie = (Movie)query.getSingleResult();
		MovieDTO movieDTO = new MovieDTO();
		movieDTO.setDirectorName(movie.getDirectorName());
		movieDTO.setImdbRating(movie.getImdbRating());
		movieDTO.setMovieId(movie.getMovieId());
		movieDTO.setMovieName(movie.getMovieName());
		movieDTO.setYear(movie.getYear());
		return movieDTO;
	}

	@Override
	public List<Object[]> getMoviesNameAndYear(String directorName) {
		String stringQuery = "SELECT m.movieName, m.year from Movie m where m.directorName = ?1";
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter(1, directorName);
		List<Object[]> result = query.getResultList();
		return result;
	}

}

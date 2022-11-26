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
public class MovieRepositoryImpl implements MovieRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<MovieDTO> getMoviesByImdbRating(Double fromRating, Double toRating) {
		/*
		 * - This method fetches the details of movies which has rating between
		 * fromRating and toRating. - If no movies are found, then return an empty list
		 * Else, return the list of movie details
		 */
		List<MovieDTO> movieDTOs = null;
		String stringQuery = "SELECT m from Movie m where m.imdbRating between ?1 and ?2 and m.imdbRating is not null";
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter(1, fromRating);
		query.setParameter(2, toRating);
		List<Movie> movies = query.getResultList();
		movieDTOs = new ArrayList<>();
		for (Movie movie : movies) {
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
		Movie movie = (Movie) query.getSingleResult();
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

	@Override
	public List<MovieDTO> getMovieByRating(Double fromRating) {
		List<MovieDTO> movieDTOs = null;
		String stringQuery = "select m from Movie m where m.imdbRating = ?1";
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter(1, fromRating);
		List<Movie> movies = query.getResultList();
		movieDTOs = new ArrayList<>();
		for (Movie movie : movies) {
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
	public List<MovieDTO> getHighestRatedMovie(String directorName) {
		List<MovieDTO> movieDTOs = null;
		String stringQuery = "select m from Movie m where m.directorName = ?1 and m.imdbRating in (select max(m.imdbRating) from Movie m where m.directorName = ?2 )";
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter(1, directorName);
		query.setParameter(2, directorName);	
		List<Movie> movies = query.getResultList();
		movieDTOs = new ArrayList<>();
		for (Movie movie : movies) {
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
	public Double getAverageDirectorRating(String directorName) {
		String stringQuery = "select AVG(m.imdbRating) from Movie m where m.directorName = ?1";
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter(1, directorName);
		return (Double)query.getSingleResult();
	}

	@Override
	public Long getNumberOfMoviesReleased(Integer fromYear, Integer toYear) {
		String stringQuery = "select count(m.movieId) from Movie m where m.year between ?1 and ?2";
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter(1, fromYear);
		query.setParameter(2, toYear);
		return (Long)query.getSingleResult();
	}

}

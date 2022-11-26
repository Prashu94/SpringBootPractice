package com.infosys;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infosys.dto.MovieDTO;
import com.infosys.service.MovieService;

@SpringBootApplication
public class DemoJpqlExerciseApplication implements CommandLineRunner{
	private static final Logger LOGGER = LogManager.getLogger(DemoJpqlExerciseApplication.class);
	
	@Autowired
	MovieService movieService;
	@Autowired
	Environment environment;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoJpqlExerciseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Exercise 1
		//getMovieByName();
		//getMovieByImdbRating();
		//getMovieNameAndYear();
		
		// Exercise 2
		getMovieByRating();
		getHighestRatedMovie();
		getAverageDirectorRating();
		getNumberOfMoviesReleased();
	}
	
	public void getMovieByName() {
		try {
			String movieName = "Deadpool";
			MovieDTO movieDTO = movieService.getMovieByName(movieName);
			LOGGER.info(movieDTO);
		}catch(Exception e) {
			LOGGER.info(environment.getProperty(e.getMessage(), "Some exception occured.Please check log file"));
			LOGGER.info("\n");
		}
	}
	
	public void getMovieByImdbRating() {
		try {
			Double fromRating = 0.0d;
			Double toRating = 7.8d;
			List<MovieDTO> movieDTOs = movieService.getMoviesByImdbRating(fromRating, toRating);
			for(MovieDTO movieDTO: movieDTOs) {
				LOGGER.info(movieDTO);
			}
			LOGGER.info("\n");
		}catch(Exception e) {
			LOGGER.info(environment.getProperty(e.getMessage(), "Some exception occured. Please check log file"));
			LOGGER.info("\n");
		}
	}
	
	public void getMovieNameAndYear() {
		try {
			String directorName = "Joss Whedon";
			List<Object[]> objects = movieService.getMoviesByNameAndYear(directorName);
			for(Object[] object: objects) {
				LOGGER.info(object[0] + "\t" + object[1]);
			}
			LOGGER.info("\n");
		}catch(Exception e) {
			LOGGER.info(environment.getProperty(e.getMessage(), "Some exception occured. Please check log file"));
			LOGGER.info("\n");
		}
	}
	
	public void getMovieByRating() {
		try {
			Double fromRating = 7.6d;
			List<MovieDTO> movieDTOs = movieService.getMovieByRating(fromRating);
			movieDTOs.forEach(LOGGER::info);
		}catch(Exception e) {
			LOGGER.info(environment.getProperty(e.getMessage(), "Some exception occured. Please check log file"));
			LOGGER.info("\n");
		}
		LOGGER.info("\n");
	}
	
	public void getHighestRatedMovie() {
		try {
			String directorName = "Joss Whedon";
			List<MovieDTO> movieDTOs = movieService.getHighestRatedMovie(directorName);
			movieDTOs.forEach(LOGGER::info);
		}catch(Exception e) {
			LOGGER.info(environment.getProperty(e.getMessage(), "Some exception occured. Please check log file"));
			LOGGER.info("\n");
		}
		LOGGER.info("\n");
	}
	
	public void getAverageDirectorRating() {
		try {
			String directorName = "Tim Miller";
			Double avgRating = movieService.getAverageDirectorRating(directorName);
			LOGGER.info(environment.getProperty("UserInterface.MOVIE_AVG_RATING") + directorName + ":" + avgRating);
		}catch(Exception e) {
			LOGGER.info(environment.getProperty(e.getMessage(), "Some exception occured. Please check log file"));
			LOGGER.info("\n");
		}
		LOGGER.info("\n");
	}
	
	public void getNumberOfMoviesReleased() {
		try {
			Integer fromYear = 2012;
			Integer toYear = 2016;
			Long noOfMoviesReleased = movieService.getNumberOfMoviesReleased(fromYear, toYear);
			LOGGER.info(environment.getProperty("UserInterface.MOVIE_YEAR_BETWEEN") + fromYear
					+ " and " + toYear+ " = "+noOfMoviesReleased);
			LOGGER.info("\n");
		} catch (Exception e) {
			LOGGER.info(environment.getProperty(e.getMessage(), "Some exception occured.Please check log file."));
		}
		LOGGER.info("\n");
	}

}

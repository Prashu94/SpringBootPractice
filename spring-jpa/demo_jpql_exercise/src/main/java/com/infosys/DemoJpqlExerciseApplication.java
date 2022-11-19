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
		getMovieByName();
		getMovieByImdbRating();
		getMovieNameAndYear();
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

}

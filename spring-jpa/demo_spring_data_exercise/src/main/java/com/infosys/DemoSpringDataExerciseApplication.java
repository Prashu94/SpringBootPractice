package com.infosys;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infosys.dto.BookDTO;
import com.infosys.service.BookService;

@SpringBootApplication
public class DemoSpringDataExerciseApplication implements CommandLineRunner{
	private static final Log LOGGER = LogFactory.getLog(DemoSpringDataExerciseApplication.class);
	@Autowired
	private BookService bookService;
	
	@Autowired
	private Environment environment;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringDataExerciseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("getBookDetails");
		getBookDetails();
		//LOGGER.info("addBooks");
		//addBook();
		LOGGER.info("getBookByAuthorName");
		getBookByAuthorName();
		LOGGER.info("getBookGreaterThanEqualToPrice");
		getBookGreaterThanEqualToPrice();
		LOGGER.info("getBookLessThanPrice");
		getBookLessThanPrice();
		LOGGER.info("bookPublishedBetweenYear");
		bookPublishedBetweenYear();
		LOGGER.info("bookPublishedAfterYear");
		bookPublishedAfterYear();
		LOGGER.info("getBookByAuthorNameAndPublisher");
		getBookByAuthorNameAndPublisher();
		LOGGER.info("updatePrice");
		updateBookPrice();
		LOGGER.info("deletePrice");
		deleteBookPrice();
	}
	
	
	public void getBookDetails() {
		try {
			BookDTO bookDTO = bookService.getBookDetails(1001);
			LOGGER.info(bookDTO);
		}catch(Exception e) {
			if (e.getMessage() != null) {
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
			}
		}
	}
	
	public void addBook() {
		BookDTO book = new BookDTO();
		book.setAuthorName("Elin Hilderbrand");
		book.setBookId(1012);
		book.setIsbn(12345690002L);
		book.setPrice(675);
		book.setPublishedYear(LocalDate.of(2022, 06, 14));
		book.setPublisher("Atria Books");
		book.setTitle("The Hotel Nantucket");
		
		try {
			bookService.addBook(book);
			LOGGER.info(environment.getProperty("UserInterface.INSERT_SUCCESS"));
		}catch(Exception e) {
			if (e.getMessage() != null) {
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
			}
		}
	}
	
	public void getBookByAuthorName() {
		try {
			List<BookDTO> bookDTOs = bookService.getBookByAuthorName("Nicholas Sparks");
			bookDTOs.forEach(bookDTO -> {
				LOGGER.info(bookDTO);
			});
			LOGGER.info("\n");
		}catch(Exception e) {
			if (e.getMessage() != null) {
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
			}
		}
	}
	
	public void getBookGreaterThanEqualToPrice() {
		try {
			List<BookDTO> bookDTOs = bookService.getBookGreaterThanEqualToPrice(700);
			bookDTOs.forEach(bookDTO -> {
				LOGGER.info(bookDTO);
			});
			LOGGER.info("\n");
		}catch(Exception e) {
			if (e.getMessage() != null) {
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
			}
		}
	}
	
	public void getBookLessThanPrice() {
		try {
			List<BookDTO> bookDTOs = bookService.getBookLessThanPrice(600);
			bookDTOs.forEach(bookDTO -> {
				LOGGER.info(bookDTO);
			});
			LOGGER.info("\n");
		}catch(Exception e) {
			if (e.getMessage() != null) {
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
			}
		}
	}
	
	public void bookPublishedBetweenYear() {
		try {
			List<BookDTO> bookDTOs = bookService.bookPublishedBetweenYear(LocalDate.of(1990, 12, 21), LocalDate.of(2000, 12, 22));
			bookDTOs.forEach(bookDTO -> {
				LOGGER.info(bookDTO);
			});
			LOGGER.info("\n");
		}catch(Exception e) {
			if (e.getMessage() != null) {
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
			}
		}
	}
	
	public void bookPublishedAfterYear() {
		try {
			List<BookDTO> bookDTOs = bookService.bookPublishedAfterYear(LocalDate.of(2022, 01, 12));
			bookDTOs.forEach(bookDTO -> {
				LOGGER.info(bookDTO);
			});
			LOGGER.info("\n");
		}catch(Exception e) {
			if (e.getMessage() != null) {
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
			}
		}
	}
	
	public void getBookByAuthorNameAndPublisher() {
		try {
			List<BookDTO> bookDTOs = bookService.getBookByAuthorNameAndPublisher("Amish Tripathi", "Westland Press");
			bookDTOs.forEach(bookDTO -> {
				LOGGER.info(bookDTO);
			});
			LOGGER.info("\n");
		}catch(Exception e) {
			if (e.getMessage() != null) {
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
			}
		}
	}
	
	public void updateBookPrice() {
		try {
			bookService.updateBookPrice(1012, 750);
			LOGGER.info(environment.getProperty("UserInterface.UPDATE_SUCCESS"));
		}catch(Exception e) {
			if (e.getMessage() != null) {
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
			}
		}
	}
	
	public void deleteBookPrice() {
		try {
			bookService.deleteBook(1011);
			LOGGER.info(environment.getProperty("UserInterface.DELETE_SUCCESS"));
		}catch(Exception e) {
			if (e.getMessage() != null) {
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
			}
		}
	}
}

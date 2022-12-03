package com.infosys.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.dto.BookDTO;
import com.infosys.entity.Book;
import com.infosys.exception.InfyBookException;
import com.infosys.repository.BookRepository;
import com.infosys.utility.ConvertEntityToDTO;
import com.infosys.validator.Validator;

@Service(value = "bookService")
@Transactional
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public BookDTO getBookDetails(Integer bookId) throws InfyBookException {
		Optional<Book> optional = bookRepository.findById(bookId);
		Book book = optional.orElseThrow(() -> new InfyBookException("Service.BOOK_DETAILS_NOT_FOUND"));
		BookDTO bookDTO = new BookDTO();
		bookDTO.setAuthorName(book.getAuthorName());
		bookDTO.setBookId(book.getBookId());
		bookDTO.setIsbn(book.getIsbn());
		bookDTO.setPrice(book.getPrice());
		bookDTO.setPublishedYear(book.getPublishedYear());
		bookDTO.setPublisher(book.getPublisher());
		bookDTO.setTitle(book.getTitle());
		return bookDTO;
	}

	@Override
	public void addBook(BookDTO bookDTO) throws InfyBookException {
		Optional<Book> optional = bookRepository.findById(bookDTO.getBookId());
		if(optional.isPresent()) {
			throw new InfyBookException("Service.BOOK_ALREADY_PRESENT");
		}
		Book book = new Book();
		book.setAuthorName(bookDTO.getAuthorName());
		book.setBookId(bookDTO.getBookId());
		book.setIsbn(bookDTO.getIsbn());
		book.setPrice(bookDTO.getPrice());
		book.setPublishedYear(bookDTO.getPublishedYear());
		book.setPublisher(bookDTO.getPublisher());
		book.setTitle(bookDTO.getTitle());
		
		if(Validator.validate(book)) {
			bookRepository.save(book);
		}else{
			throw new InfyBookException("Validator.INVALID_YEAR");
		}
		
	}

	@Override
	public List<BookDTO> getBookByAuthorName(String authorName) throws InfyBookException {
		List<Book> books = bookRepository.findByAuthorName(authorName);
		List<BookDTO> bookDTOs = new ArrayList<BookDTO>();
		
		if(books.isEmpty()) {
			throw new InfyBookException("Service.BOOK_NOT_FOUND");
		}
		
		books.forEach(book -> {
			BookDTO bookDTO = ConvertEntityToDTO.convertBookEntityToDTO(book);
			bookDTOs.add(bookDTO);
		});
		
		return bookDTOs;
	}

	@Override
	public List<BookDTO> getBookGreaterThanEqualToPrice(Integer price) throws InfyBookException {
		List<Book> books = bookRepository.findByPriceGreaterThanEqual(price);
		List<BookDTO> bookDTOs = new ArrayList<BookDTO>();
		
		if(books.isEmpty()) {
			throw new InfyBookException("Service.BOOK_NOT_FOUND");
		}
		
		books.forEach(book -> {
			BookDTO bookDTO = ConvertEntityToDTO.convertBookEntityToDTO(book);
			bookDTOs.add(bookDTO);
		});
		return bookDTOs;
	}

	@Override
	public List<BookDTO> getBookLessThanPrice(Integer price) throws InfyBookException {
		List<Book> books = bookRepository.findByPriceLessThan(price);
		List<BookDTO> bookDTOs = new ArrayList<BookDTO>();
		
		if(books.isEmpty()) {
			throw new InfyBookException("Service.BOOK_NOT_FOUND");
		}
		
		books.forEach(book -> {
			BookDTO bookDTO = ConvertEntityToDTO.convertBookEntityToDTO(book);
			bookDTOs.add(bookDTO);
		});
		return bookDTOs;
	}

	@Override
	public List<BookDTO> bookPublishedBetweenYear(LocalDate startYear, LocalDate endYear) throws InfyBookException {
		List<Book> books = bookRepository.findByPublishedYearBetween(startYear, endYear);
		List<BookDTO> bookDTOs = new ArrayList<BookDTO>();
		
		if(books.isEmpty()) {
			throw new InfyBookException("Service.BOOK_NOT_FOUND");
		}
		
		books.forEach(book -> {
			BookDTO bookDTO = ConvertEntityToDTO.convertBookEntityToDTO(book);
			bookDTOs.add(bookDTO);
		});
		return bookDTOs;
	}

	@Override
	public List<BookDTO> bookPublishedAfterYear(LocalDate year) throws InfyBookException {
		List<Book> books = bookRepository.findByPublishedYearAfter(year);
		List<BookDTO> bookDTOs = new ArrayList<BookDTO>();
		if(books.isEmpty()) {
			throw new InfyBookException("Service.BOOK_NOT_FOUND");
		}
		
		books.forEach(book -> {
			BookDTO bookDTO = ConvertEntityToDTO.convertBookEntityToDTO(book);
			bookDTOs.add(bookDTO);
		});
		return bookDTOs;
	}

	@Override
	public List<BookDTO> getBookByAuthorNameAndPublisher(String authorName, String publisherName)
			throws InfyBookException {
		List<Book> books = bookRepository.findByAuthorNameAndPublisher(authorName, publisherName);
		List<BookDTO> bookDTOs = new ArrayList<BookDTO>();
		if(books.isEmpty()) {
			throw new InfyBookException("Service.BOOK_NOT_FOUND");
		}
		
		books.forEach(book -> {
			BookDTO bookDTO = ConvertEntityToDTO.convertBookEntityToDTO(book);
			bookDTOs.add(bookDTO);
		});
		return bookDTOs;
	}

	@Override
	public void updateBookPrice(Integer bookId, Integer price) throws InfyBookException {
		Optional<Book> optional = bookRepository.findById(bookId);
		Book book = optional.orElseThrow(()-> new InfyBookException("Service.BOOK_NOT_FOUND"));
		book.setPrice(price);
		bookRepository.save(book);
	}

	@Override
	public void deleteBook(Integer bookId) throws InfyBookException {
		Optional<Book> optional = bookRepository.findById(bookId);
		Book book = optional.orElseThrow(() -> new InfyBookException("Service.BOOK_NOT_FOUND"));
		bookRepository.delete(book);
	}

}

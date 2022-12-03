package com.infosys.service;

import java.time.LocalDate;
import java.util.List;

import com.infosys.dto.BookDTO;
import com.infosys.entity.Book;
import com.infosys.exception.InfyBookException;

public interface BookService {
	
	public BookDTO getBookDetails(Integer bookId) throws InfyBookException;
	public void addBook(BookDTO bookDTO) throws InfyBookException;
	public List<BookDTO> getBookByAuthorName(String authorName) throws InfyBookException;
	public List<BookDTO> getBookGreaterThanEqualToPrice(Integer price) throws InfyBookException;
	public List<BookDTO> getBookLessThanPrice(Integer price) throws InfyBookException;
	public List<BookDTO> bookPublishedBetweenYear(LocalDate startYear, LocalDate endYear) throws InfyBookException;
	public List<BookDTO> bookPublishedAfterYear(LocalDate year) throws InfyBookException;
	public List<BookDTO> getBookByAuthorNameAndPublisher(String authorName, String publisherName) throws InfyBookException;
	public void updateBookPrice(Integer bookId, Integer price) throws InfyBookException;
	public void deleteBook(Integer bookId) throws InfyBookException;
}

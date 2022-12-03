package com.infosys.utility;

import com.infosys.dto.BookDTO;
import com.infosys.entity.Book;

public class ConvertEntityToDTO {
	
	public static BookDTO convertBookEntityToDTO(Book book) {
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
}

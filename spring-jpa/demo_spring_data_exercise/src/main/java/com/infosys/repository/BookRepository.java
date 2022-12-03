package com.infosys.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.infosys.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer>{
	
	List<Book> findByAuthorName(String name);
	List<Book> findByPriceGreaterThanEqual(Integer price);
	List<Book> findByPriceLessThan(Integer price);
	List<Book> findByPublishedYearBetween(LocalDate fromDate, LocalDate toDate);
	List<Book> findByPublishedYearAfter(LocalDate publishedDate);
	List<Book> findByAuthorNameAndPublisher(String authorName, String publisher);
}

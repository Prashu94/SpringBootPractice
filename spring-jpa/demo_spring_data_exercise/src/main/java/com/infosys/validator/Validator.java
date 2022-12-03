package com.infosys.validator;

import java.time.LocalDate;

import com.infosys.entity.Book;

public class Validator {
	/*
	 * 1. The method calls the validateYear method for publishedYear*/
	public static boolean validate(Book book) {
		return validateYear(book.getPublishedYear());
	}
	/*
	 * 1. validateYear(LocalDate year) 
	 * - it should not be after the present date
	 * - it should be before present date*/
	public static boolean validateYear(LocalDate year) {
		if(!year.isAfter(LocalDate.now()) && year.isBefore(LocalDate.now())) {
			return true;
		}
		return false;
	}
}

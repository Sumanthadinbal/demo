package com.example.service;

import java.util.List;

import com.example.dto.BookDTO;

public interface BookService {

	public List<BookDTO> getAllBooks(String sortBy);
	
	/**
	 * This method takes id as input and return Book object
	 * @param id
	 * @return
	 */
	public BookDTO getBook(Integer id);
	
/**
 * This method takes authorName as input and returns List of books
 * @param authorName
 * @return
 */
	public List<BookDTO> getBook(String authorName);
}

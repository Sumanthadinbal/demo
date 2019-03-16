package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BookDTO;
import com.example.service.BookService;

@RestController
public class BooksController {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/api/v1/books", method = RequestMethod.GET)
	public ResponseEntity<?> getAllBooks(@RequestParam("sortBy") String sortBy) {
		List<BookDTO> books = bookService.getAllBooks(sortBy);
		if (books == null || books.isEmpty()) {
			return new ResponseEntity<String>("No Books Available", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List>(books, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/api/v1/books/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getBook(@PathVariable("id") Integer id) {
		
		BookDTO book = bookService.getBook(id);
		if (book == null) {
			return new ResponseEntity<String>("No Book Available", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<BookDTO>(book, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/api/v1/books/author/{authorName}", method = RequestMethod.GET)
	public ResponseEntity<?> getBook(@PathVariable("authorName") String authorName){
		List<BookDTO> books=bookService.getBook(authorName);
		if(books == null || books.isEmpty()) {
			return new ResponseEntity<String>("This author has no books", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List>(books, HttpStatus.OK);
		}
	}

}

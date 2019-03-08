package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BookDTO;
import com.example.service.BookService;

@RestController
public class BooksController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/api/v1/books",method=RequestMethod.GET)
	public List<BookDTO> getAllBooks(){
		
		return bookService.getAllBooks();
		
	}
	
}

package com.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.BooksRepository;
import com.example.dto.BookDTO;
import com.example.entity.Book;
import com.example.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BooksRepository bookRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@PostConstruct
	void populate(){
		bookRepository.save(new Book(1,"One indian girl","Chetan Bhagat"));
	}
	@Override
	public List<BookDTO> getAllBooks() {
		// TODO Auto-generated method stub
		List<Book> books=bookRepository.findAll();
		return books.stream().map(book->objectMapper.convertValue(book, BookDTO.class)).collect(Collectors.toList());
	}

}

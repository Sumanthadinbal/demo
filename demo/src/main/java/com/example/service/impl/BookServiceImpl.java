package com.example.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.dao.BooksRepository;
import com.example.dto.BookDTO;
import com.example.entity.Book;
import com.example.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BooksRepository bookRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	void populate() {
		bookRepository.save(new Book(1, "One indian girl", "Chetan"));
		bookRepository.save(new Book(2, "Three mistakes of my life", "Chetan"));
		bookRepository.save(new Book(3, "ABCD", "Sumant"));
		bookRepository.save(new Book(4, "XYZ", "Ram"));
	}

	@Override
	public List<BookDTO> getAllBooks(String sortBy) {
		// TODO Auto-generated method stub
		Sort sort = null;
		List<Book> books;
		if (sortBy != null && sortBy.startsWith("-")) {
			sort = new Sort(Sort.Direction.DESC, sortBy.substring(1, sortBy.length()));
		} else if (sortBy != null) {
			sort = new Sort(Sort.Direction.ASC, sortBy.substring(0, sortBy.length()));
		}
		if (sort != null) {
			books = bookRepository.findAll(sort);
		} else {
			books = bookRepository.findAll();
		}
		return books.stream().map(book -> objectMapper.convertValue(book, BookDTO.class)).collect(Collectors.toList());
	}

	@Override
	public BookDTO getBook(Integer id) {
		Optional<Book> book = bookRepository.findById(id);
		BookDTO bookDTO = null;
		if (book.isPresent()) {
			bookDTO = objectMapper.convertValue(book, BookDTO.class);
		}
		return bookDTO;
	}

	@Override
	public List<BookDTO> getBook(String authorName) {
		List<Book> books = bookRepository.findByAuthorName(authorName);
		return books.stream().map(book -> objectMapper.convertValue(book, BookDTO.class)).collect(Collectors.toList());
	}

}

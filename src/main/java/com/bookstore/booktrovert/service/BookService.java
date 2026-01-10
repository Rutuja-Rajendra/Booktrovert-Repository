package com.bookstore.booktrovert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.booktrovert.entity.Book;
import com.bookstore.booktrovert.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public Book addBook(Book book)
	{
		return bookRepository.save(book);
	}
	
	public List<Book> getAllBooks()
	{
		return bookRepository.findAll();
	}
}

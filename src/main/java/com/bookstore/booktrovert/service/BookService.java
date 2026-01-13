package com.bookstore.booktrovert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.booktrovert.entity.Book;
import com.bookstore.booktrovert.repository.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;
	
	public BookService (BookRepository bookRepository)
	{
		this.bookRepository = bookRepository;
	}
	
	
	public Book addBook(Book book)
	{
		return bookRepository.save(book);
	}
	
	public List<Book> getAllBooks()
	{
		return bookRepository.findAll();
	}
	
	public Book updateQuantity (Long id, int quantity)
	{
		Book book = bookRepository.findById(id).
				orElseThrow(() -> new RuntimeException("Book not found"));
		
		book.setQuantity(quantity);
		return bookRepository.save(book);
	}
	
	public void deleteBook (Long id)
	{
		bookRepository.deleteById(id);
	}
}

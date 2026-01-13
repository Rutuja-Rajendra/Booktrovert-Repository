package com.bookstore.booktrovert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.booktrovert.entity.Book;
import com.bookstore.booktrovert.service.BookService;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {

	@Autowired
	private BookService bookService;
	
	@PostMapping
	public Book addBook(@RequestBody Book book)
	{
		return bookService.addBook(book);
	}
	
	@GetMapping
	public List<Book> getAllBooks()
	{
		return bookService.getAllBooks();
	}
	
	@PutMapping("/{id}")
	public Book updateQuantity(@PathVariable Long id, @RequestParam int quantity)
	{
		return bookService.updateQuantity(id, quantity);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Long id)
	{
		bookService.deleteBook(id);
	}
}

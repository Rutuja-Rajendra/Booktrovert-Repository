package com.bookstore.booktrovert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.booktrovert.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}

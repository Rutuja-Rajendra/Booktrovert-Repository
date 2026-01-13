package com.bookstore.booktrovert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.booktrovert.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Long>{

}

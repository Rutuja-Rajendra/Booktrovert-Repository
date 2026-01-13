package com.bookstore.booktrovert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.booktrovert.entity.BillItem;

public interface BillItemRepository extends JpaRepository<BillItem, Long>{

	
}

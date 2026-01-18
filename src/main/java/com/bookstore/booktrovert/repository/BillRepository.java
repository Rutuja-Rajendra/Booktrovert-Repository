package com.bookstore.booktrovert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookstore.booktrovert.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Long>{

	@Query("SELECT SUM(b.totalAmount) FROM Bill b")
	Double getTotalRevenue();
	
	@Query("SELECT SUM(b.totalAmount) FROM Bill b WHERE DATE(b.billDate) = CURRENT_DATE")
	Double getTotalSales();
	
	

}

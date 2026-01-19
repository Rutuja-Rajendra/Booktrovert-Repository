package com.bookstore.booktrovert.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookstore.booktrovert.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Long>{

	@Query("SELECT SUM(b.totalAmount) FROM Bill b")
	Double getTotalRevenue();
	
	@Query("""
		    SELECT SUM(b.totalAmount)
		    FROM Bill b
		    WHERE b.billDate BETWEEN :start AND :end
		""")
		Double getTodaySales(
		        @Param("start") LocalDateTime start,
		        @Param("end") LocalDateTime end
		);

	
	

}

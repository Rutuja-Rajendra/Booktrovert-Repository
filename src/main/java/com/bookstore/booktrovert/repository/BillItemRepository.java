package com.bookstore.booktrovert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookstore.booktrovert.entity.BillItem;

public interface BillItemRepository extends JpaRepository<BillItem, Long>{

	List<BillItem> findByBillId(Long billId);
	
	@Query("""
		    SELECT bi.bookName, SUM(bi.quantity)
		    FROM BillItem bi
		    GROUP BY bi.bookName
		    ORDER BY SUM(bi.quantity) DESC
		""")
		List<Object[]> getTopSellingBooks();
}

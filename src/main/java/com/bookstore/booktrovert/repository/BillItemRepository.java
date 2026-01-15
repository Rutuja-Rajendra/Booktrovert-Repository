package com.bookstore.booktrovert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.booktrovert.entity.BillItem;

public interface BillItemRepository extends JpaRepository<BillItem, Long>{

	List<BillItem> findByBillId(Long billId);
}

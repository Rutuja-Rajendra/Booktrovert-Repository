package com.bookstore.booktrovert.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.booktrovert.repository.BillItemRepository;
import com.bookstore.booktrovert.repository.BillRepository;

@Service
public class ReportService {
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private BillItemRepository billItemRepository;

	public double getTotalRevenu()
	{
		Double revenue = billRepository.getTotalRevenue();
		return revenue != null ? revenue : 0.0;
	}
	
	public long getTotalBills()
	{
		return billRepository.count();
	}
	
	public double getTodaySales() {

	    LocalDate today = LocalDate.now();

	    LocalDateTime startOfDay = today.atStartOfDay();
	    LocalDateTime endOfDay = today.atTime(LocalTime.MAX);

	    Double amount = billRepository.getTodaySales(startOfDay, endOfDay);

	    return amount != null ? amount : 0.0;
	}

	
	public List<String> getTopSellingBooks()
	{
		return billItemRepository.getTopSellingBooks()
				.stream()
				.map(obj -> obj[0] +" - " +obj[1])
				.toList();
	}
}

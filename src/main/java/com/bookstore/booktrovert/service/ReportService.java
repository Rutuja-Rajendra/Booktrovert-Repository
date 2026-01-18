package com.bookstore.booktrovert.service;

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
	
	public double getTotalSale()
	{
		Double amount = billRepository.getTotalSales();
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

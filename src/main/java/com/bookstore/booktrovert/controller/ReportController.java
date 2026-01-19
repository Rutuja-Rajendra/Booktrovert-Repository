package com.bookstore.booktrovert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.booktrovert.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/revenue")
	public double getTotalRevenue()
	{
		return reportService.getTotalRevenu();
	}
	
	
	@GetMapping("/total-bills")
	public long getTotalBills()
	{
		return reportService.getTotalBills();
	}
	
	@GetMapping("/total-sales")
	public double getTotalSales()
	{
		return reportService.getTodaySales();
	}
	
	@GetMapping("/top-books")
	public List<String> getTopSellingBooks()
	{
		return reportService.getTopSellingBooks();
	}
}
	
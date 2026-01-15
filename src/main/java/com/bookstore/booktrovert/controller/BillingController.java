package com.bookstore.booktrovert.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.booktrovert.dto.BillRequestDTO;
import com.bookstore.booktrovert.entity.Bill;
import com.bookstore.booktrovert.entity.BillItem;
import com.bookstore.booktrovert.service.BillingService;

@RestController
@RequestMapping("/api/billing")
@CrossOrigin
public class BillingController {

	private final BillingService billingService;
	
	public BillingController(BillingService billingService)
	{
		this.billingService = billingService;
	}
	
	@PostMapping
	public Bill generateBill(@RequestBody BillRequestDTO request)
	{
		return billingService.generateBill(request);
	}
	
	
	@GetMapping("/{billId}/items")
	public List<BillItem> getBillItems(@PathVariable Long billId)
	{
		return billingService.getBillItems(billId);
	}
}

package com.bookstore.booktrovert.service;

import javax.management.JMRuntimeException;

import org.springframework.stereotype.Service;

import com.bookstore.booktrovert.dto.BillRequestDTO;
import com.bookstore.booktrovert.entity.Bill;
import com.bookstore.booktrovert.entity.BillItem;
import com.bookstore.booktrovert.entity.Book;
import com.bookstore.booktrovert.repository.BillItemRepository;
import com.bookstore.booktrovert.repository.BillRepository;
import com.bookstore.booktrovert.repository.BookRepository;

@Service
public class BillingService {

	private final BookRepository bookRepository;
	private final BillRepository billRepository;
	private final BillItemRepository billItemRepository;
	
	public BillingService(BookRepository bookRepository, 
			BillRepository billRepository, BillItemRepository billItemRepository)
	{
		this.billItemRepository = billItemRepository;
		this.billRepository = billRepository;
		this.bookRepository = bookRepository;
	}
	
	public Bill generateBill(BillRequestDTO request)
	{
		Bill bill = new Bill();
		double total = 0;
		
		bill = billRepository.save(bill);
		
		
		for(BillRequestDTO.Item item : request.getItems())
		{
			Book book = bookRepository.findById(item.getBookId())
			.orElseThrow(() -> new RuntimeException("Book not found"));
			
			
			if(book.getQuantity() < item.getQuantity())
			{
				throw new JMRuntimeException("Insufficient Stock");
			}
			
			//reduce inventory
			book.setQuantity(book.getQuantity() - item.getQuantity());
			bookRepository.save(book);
			
			
			
			BillItem billItem = new BillItem();
			billItem.setBookId(book.getId());
			billItem.setBookName(book.getName());
			billItem.setQuantity(item.getQuantity());
			billItem.setPrice(book.getPrice());
			billItem.setBill(bill);
			
			
			billItemRepository.save(billItem);
			
			total += book.getPrice() * item.getQuantity();
			
		}
		
		bill.setTotalAmount(total);
		return billRepository.save(bill);
	}
}

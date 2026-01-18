package com.bookstore.booktrovert.service;

import java.util.List;

import javax.management.JMRuntimeException;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bookstore.booktrovert.dto.BillItemResponseDTO;
import com.bookstore.booktrovert.dto.BillRequestDTO;
import com.bookstore.booktrovert.dto.BillResponseDTO;
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
	
	
//	public List<BillItem> getBillItems(Long billId) 
//	{
//        return billItemRepository.findByBillId(billId);
//    }
	
	
	public BillResponseDTO getBillWithItems(Long billId)
	{
		Bill bill = billRepository.findById(billId)
				.orElseThrow(() -> new RuntimeException("Bill Not found"));
		
		
		List<BillItem> billItems = billItemRepository.findByBillId(billId);
		
		List<BillItemResponseDTO> itemsDTO = billItems.stream().map(item -> {
			BillItemResponseDTO dto = new BillItemResponseDTO();
			dto.setBookId(item.getBookId());
			dto.setBookName(item.getBookName());
			dto.setQuantity(item.getQuantity());
			dto.setPrice(item.getPrice());
			
			return dto;
		}).collect(Collectors.toList());
		
		BillResponseDTO response = new BillResponseDTO();
		response.setBillID(bill.getId());
		response.setBillDate(bill.getBillDate());
		response.setItems(itemsDTO);
		response.setTotalPrice(bill.getTotalAmount());
		
		return response;
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
				throw new RuntimeException("Insufficient Stock");
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

package com.bookstore.booktrovert.dto;

import java.util.List;

public class BillRequestDTO {

	private List<Item> items;
	
	public List<Item> getItems()
	{
		return items;
	}
	
	public void setItems(List<Item> items)
	{
		this.items = items;
	}
	
	public static class Item 
	{
		private Long bookId;
		private int quantity;
		
		public Long getBookId() {
			return bookId;
		}
		public void setBookId(Long bookId) {
			this.bookId = bookId;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
		
	}
}

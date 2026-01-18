package com.bookstore.booktrovert.dto;

import java.time.LocalDateTime;
import java.util.List;

public class BillResponseDTO {

	private Long billID;
	private LocalDateTime billDate;
	private double totalPrice;
	private List<BillItemResponseDTO> items;
	
	public Long getBillID() {
		return billID;
	}
	public void setBillID(Long billID) {
		this.billID = billID;
	}
	public LocalDateTime getBillDate() {
		return billDate;
	}
	public void setBillDate(LocalDateTime billDate) {
		this.billDate = billDate;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<BillItemResponseDTO> getItems() {
		return items;
	}
	public void setItems(List<BillItemResponseDTO> items) {
		this.items = items;
	}
	
	
}

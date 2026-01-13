package com.bookstore.booktrovert.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "biils")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime billDate;
	private double totalAmount;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getBillDate() {
		return billDate;
	}
	public void setBillTime() {
		billDate = LocalDateTime.now();
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public Bill()
	{
		this.billDate = LocalDateTime.now();
	}
	
}

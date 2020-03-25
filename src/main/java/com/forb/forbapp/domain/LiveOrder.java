package com.forb.forbapp.domain;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import lombok.Data;

@Data
public class LiveOrder {
	
	public LiveOrder(String order_type2, int order_price2, int order_quantity2) {
		// 
		LocalDateTime date = LocalDateTime.now();
		this.id = date.toEpochSecond(ZoneOffset.UTC);
		this.order_type = order_type2;
		this.order_price = order_price2;
		this.order_quantity = order_quantity2;
		this.createDateTime = date;
	}

	private long id;

	private String order_type;

	private int order_price;

	private int order_quantity;

	private LocalDateTime createDateTime;

}

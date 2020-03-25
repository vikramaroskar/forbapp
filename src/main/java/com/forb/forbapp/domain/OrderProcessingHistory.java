package com.forb.forbapp.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderProcessingHistory {
	
	private int order_processing_id;
	
	private String order_type;

	private String order_price;

	private String order_quantity;

	private LocalDateTime dateTime;
	
	private int order_master_1;
	
	private int order_master_2;

}

package com.forb.forbapp.inputtypes;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class SingleOrderIto {
	
	@NotNull
	private String order_type;

	@NotNull
	@Positive
	private int order_price;

	@NotNull
	@Positive
	private int order_quantity;

}

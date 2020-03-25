package com.forb.forbapp.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.forb.forbapp.inputtypes.SingleOrderIto;
import com.forb.forbapp.services.ForbAppService;

/**
 * 
 * @author Vikram Aroskar 
 * Main com.forb.forbapp.controller for the app
 *
 */
@RestController
public class ForbAppController {
	
	@Autowired
	ForbAppService forbAppService;

	@PostMapping(path = "/place-order")
	ResponseEntity<?> placeOrder(@RequestHeader Map<String, String> headers,
			@Valid @RequestBody SingleOrderIto so) {
		
		return convertToResponse(forbAppService.placeOrder(so), HttpStatus.OK);
		
		
	}
	
	 // Method for converting output to response
    public ResponseEntity convertToResponse(Object object, HttpStatus httpStatus){


        Map<String,Integer> meta=new HashMap<>();
        meta.put("code",httpStatus.value());
        LinkedHashMap<String,Object> response=new LinkedHashMap<>();
        response.put("meta",meta);
        response.put("data",object);
        return ResponseEntity.ok(response);
    }

}

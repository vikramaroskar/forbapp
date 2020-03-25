package com.forb.forbapp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown=true)
@Configuration
public class LiveOrderMaster implements Serializable{
	
	private List<LiveOrder> listOrders = new ArrayList<LiveOrder>();
	
	@Bean("liveordermaster")
	@Scope(
			  value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public List<LiveOrder> getListofOrders() {
        return listOrders;
    }


}

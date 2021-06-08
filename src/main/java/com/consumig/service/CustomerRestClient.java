package com.consumig.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import com.consumig.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerRestClient{

	private final String END_POINT = "https://rest-api-customer.herokuapp.com/customer/";
	private WebClient webClient = WebClient.create(END_POINT);
	
	public List<Customer> getAll(){
		return this.webClient.get().uri(this.END_POINT)
				.retrieve()
				.bodyToFlux(Customer.class)
				.collectList()
				.block();
	}
	
	public void save(Customer customer) {
		try {
			this.webClient.post().uri(this.END_POINT)
			.bodyValue(customer)
			.retrieve()
			.bodyToMono(Customer.class)
			.block();
		}catch(WebClientException e) {
			log.error(e.getMessage() + " "+  e.getMostSpecificCause());
			throw e;
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}
	}
	
	
}

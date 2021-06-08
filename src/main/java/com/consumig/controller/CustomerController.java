package com.consumig.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.consumig.model.Customer;
import com.consumig.service.CustomerRestClient;

@RestController
@RequestMapping("/customer")
public class CustomerController {
		
	@Autowired
	private CustomerRestClient customerRestClient;

	@GetMapping("/")
	public List<Customer> getAll() {
		return this.customerRestClient.getAll();
	}
	
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody Customer customer){
		this.customerRestClient.save(customer);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

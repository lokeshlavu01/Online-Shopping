package com.microservices.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.customerservice.Model.Customers;
import com.microservices.customerservice.loadbalancing.ItemServiceProxy;
import com.microservices.customerservice.loadbalancing.Items;
import com.microservices.customerservice.respository.CustomerRepository;

@CrossOrigin
@RestController
@RequestMapping(path="/Customers") 
public class CustomerController {
	@Autowired 
	private CustomerRepository customerRepository;
	
	@Autowired KafkaTemplate<String, Customers> kafkaTemplate;
	
	@Value("${spring.kafka.topic.name}")
	private String topicName;
	
	@Autowired
	private ItemServiceProxy itemServiceProxy;

	@PostMapping(path="/add")
	public @ResponseBody String addNewCustomer (@RequestParam String email
			, @RequestParam String first_name , @RequestParam String last_name) {
		

		Customers n = new Customers();
		n.setEmail(email);
		n.setFirst_name(first_name);
		n.setLast_name(last_name);
		customerRepository.save(n);
	
		kafkaTemplate.send(topicName, n);
		
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Customers> getAllCustomers() {
		
		return customerRepository.findAll();
	}
	
	 @GetMapping(value = "/{name}")
	public @ResponseBody Items  findItem(@PathVariable String name) {
		 return itemServiceProxy.getItem(name);
		 
	 }
	    
}
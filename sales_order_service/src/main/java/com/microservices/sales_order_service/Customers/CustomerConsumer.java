package com.microservices.sales_order_service.Customers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.microservices.sales_order_service.Customers.Model.Customers;
import com.microservices.sales_order_service.Customers.Repository.CustomerRepository;


@Component
public class CustomerConsumer {
	
	@Autowired
	private CustomerRepository customerRepository;

	 @Value("${spring.kafka.topic.name}")
	    private String topicName;

	    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "group_id", containerFactory="customerListener")
	    public String consume(Customers customer){
	  
	    	customerRepository.save(customer);
	    	return "successful";

	    }
    

    
    
    
    
    
    
    
    
//    public void consume(ConsumerRecord<String, Customers> record){
//        LOGGER.info(String.format("Order event received in email service => %s", record.toString()));
//
//        // send an email to the customer
//        
//        Customers customer = record.value();
//    }
    
}

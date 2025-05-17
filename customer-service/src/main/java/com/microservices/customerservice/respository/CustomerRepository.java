package com.microservices.customerservice.respository;

import org.springframework.data.repository.CrudRepository;

import com.microservices.customerservice.Model.Customers;



public interface CustomerRepository extends CrudRepository<Customers, Integer>  {

}

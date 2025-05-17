package com.microservices.sales_order_service.Customers.Repository;

import org.springframework.data.repository.CrudRepository;

import com.microservices.sales_order_service.Customers.Model.Customers;


public interface CustomerRepository extends CrudRepository<Customers, Integer> {

}

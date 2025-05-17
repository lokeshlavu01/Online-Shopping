package com.microservices.sales_order_service.SalesOrder.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.sales_order_service.SalesOrder.Model.sales_order;

@Repository
public interface SalesOrderRepository extends JpaRepository<sales_order, Long> {

	List<sales_order> findByCustid(Integer cust_id);
	Optional<sales_order> findById(Integer order_id);
}


package com.microservices.sales_order_service.OrderLineItem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.sales_order_service.OrderLineItem.Model.order_line_item;

@Repository
public interface OrderLineItemRepository extends JpaRepository<order_line_item, Long> {

}
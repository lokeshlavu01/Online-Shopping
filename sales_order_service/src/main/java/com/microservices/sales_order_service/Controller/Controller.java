package com.microservices.sales_order_service.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.sales_order_service.Customers.Model.Customers;
import com.microservices.sales_order_service.Customers.Repository.CustomerRepository;
import com.microservices.sales_order_service.Items.Config.HystrixFallback;
import com.microservices.sales_order_service.Items.Model.Items;
import com.microservices.sales_order_service.OrderLineItem.Model.order_line_item;
import com.microservices.sales_order_service.OrderLineItem.Repository.OrderLineItemRepository;
import com.microservices.sales_order_service.SalesOrder.Model.sales_order;
import com.microservices.sales_order_service.SalesOrder.Proxy.SalesOrderProxy;
import com.microservices.sales_order_service.SalesOrder.Repository.SalesOrderRepository;

@RestController
@RequestMapping(path="/Orders") 
public class Controller {

	@Autowired 
	private CustomerRepository customerRepository;
	
	@Autowired
	private SalesOrderRepository salesOrderRepository;
	
	@Autowired
	private OrderLineItemRepository orderItemRepository;

	@Autowired
	private HystrixFallback fallback;

	
	@GetMapping(path="/allCustomers")
	public @ResponseBody Iterable<Customers> getAllCustomers() {
		
		return customerRepository.findAll();
	}
	
	@PostMapping(path="/create")
	public @ResponseBody String createOrder(@RequestParam Integer cust_id, @RequestParam String order_desc, @RequestParam List<String> itemName, @RequestParam List<Integer> itemQuantity ) {
		if( customerRepository.findById(cust_id) != null) {
			double cost=0;
			List<order_line_item> list = new ArrayList<order_line_item>();
			sales_order s = new sales_order();
			s.setOrder_date(LocalDate.now());
			s.setCustid(cust_id);
			s.setOrder_desc(order_desc);
			for(Integer n=0, q=0; n<itemName.size() && q<itemQuantity.size(); n++,q++) {
				order_line_item o = new order_line_item();
		    Items item = fallback.getItem(itemName.get(n));
		    o.setItem_id(item.getId());
		    o.setItemName(itemName.get(n));
		    o.setItemPrice(item.getPrice());
		    o.setItemQuantity(itemQuantity.get(q));
		    list.add(o);
		    }
			
			for(int l=0; l<list.size();l++) {
				order_line_item ol = list.get(l);
				double iq = ol.getItemQuantity();
				cost = cost + (iq*ol.getItemPrice());
			}
			s.setTotal_price(cost);
			salesOrderRepository.save(s);
			
			for(int l=0; l<list.size();l++) {
				order_line_item ol = list.get(l);
				ol.setSalesOrder(s);
				orderItemRepository.save(ol);
			}
			
			
		    return "order_created";
		}
		else {
			return "No_Customer_Found";
		}
		
		}

	
	@GetMapping(path="/OrderByCustomerId/{cust_id}")
	public @ResponseBody List<sales_order> getOrderbyID(@PathVariable Integer cust_id) {
		return salesOrderRepository.findByCustid(cust_id);
	}
	
	@GetMapping(path="/CustomerId/{cust_id}")
	public @ResponseBody List<SalesOrderProxy> getOrderbyCustomerID(@PathVariable Integer cust_id) {
		List<SalesOrderProxy> list = new ArrayList<SalesOrderProxy>();
		      SalesOrderProxy sop =new SalesOrderProxy();
		            List<sales_order> solist =salesOrderRepository.findByCustid(cust_id);
		            for(int sol=0; sol<solist.size();sol++){
		            	sales_order so = solist.get(sol);
		            	sop.setOrder_date(so.getOrder_date());
		            	sop.setOrder_desc(so.getOrder_desc());
		            	sop.setOrderid(so.getId());
		            	sop.setTotalPrice(so.getTotal_price());
		            	list.add(sop);
		            }
		            return list;
	}
	
	
	
	@GetMapping(path="/OrderItems/{order_id}")
	public @ResponseBody List<order_line_item> getAllOrderItems(@PathVariable Integer order_id) {
		Optional<sales_order> soo= salesOrderRepository.findById(order_id);
		return soo.get().getOrderLineItems();
		}	
		        
	}

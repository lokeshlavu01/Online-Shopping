package com.microservices.sales_order_service.SalesOrder.Model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.microservices.sales_order_service.OrderLineItem.Model.order_line_item;


@Entity
@Table(name = "sales_order")
public class sales_order {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@GeneratedValue(strategy=GenerationType.AUTO)
	private LocalDate order_date;
	private Integer custid;
	private String order_desc;
	private double total_price;
	
	
	@OneToMany(mappedBy = "salesOrder", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<order_line_item> orderLineItems;
	
	public List<order_line_item> getOrderLineItems() {
		return orderLineItems;
	}
	public void setOrderLineItems(List<order_line_item> orderLineItems) {
		this.orderLineItems = orderLineItems;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	public LocalDate getOrder_date() {
		return order_date;
	}
	public void setOrder_date(LocalDate order_date) {
		this.order_date = order_date;
	}
	public Integer getCustid() {
		return custid;
	}
	public void setCustid(Integer custid) {
		this.custid = custid;
	}
	public String getOrder_desc() {
		return order_desc;
	}
	public void setOrder_desc(String order_desc) {
		this.order_desc = order_desc;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	public sales_order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public sales_order(Integer id, LocalDate order_date, Integer custid, String order_desc, double total_price,
			List<order_line_item> orderLineItems) {
		super();
		this.id = id;
		this.order_date = order_date;
		this.custid = custid;
		this.order_desc = order_desc;
		this.total_price = total_price;
		this.orderLineItems = orderLineItems;
	}
	@Override
	public String toString() {
		return "sales_order [id=" + id + ", order_date=" + order_date + ", custid=" + custid + ", order_desc="
				+ order_desc + ", total_price=" + total_price + ", orderLineItems=" + orderLineItems + "]";
	}


	
	
	
	
	
	
}

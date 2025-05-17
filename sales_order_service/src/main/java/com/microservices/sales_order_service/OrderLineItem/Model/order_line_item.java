package com.microservices.sales_order_service.OrderLineItem.Model;



import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.microservices.sales_order_service.SalesOrder.Model.sales_order;


@Entity
@Table(name = "order_line_items")
public class order_line_item {
	@Id
	private Integer item_id;

	private String itemName;
	
	private Integer itemQuantity;
	
	private double itemPrice;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order_id")
	@JsonBackReference
	private sales_order salesOrder;
	
	
	public sales_order getSalesOrder() {
		return salesOrder;
	}
	public void setSalesOrder(sales_order salesOrder) {
		this.salesOrder = salesOrder;
	}
	

	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	@Override
	public String toString() {
		return "order_line_item [item_id=" + item_id + ", itemName=" + itemName + ", itemQuantity=" + itemQuantity
				+ ", itemPrice=" + itemPrice + ", salesOrder=" + salesOrder + "]";
	}
	public order_line_item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public order_line_item(Integer item_id, String itemName, Integer itemQuantity, double itemPrice,
			sales_order salesOrder) {
		super();
		this.item_id = item_id;
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
		this.itemPrice = itemPrice;
		this.salesOrder = salesOrder;
	}

	

	
	
	
	

}

package com.microservices.sales_order_service.OrderLineItem.Proxy;

public class OrderLineItemProxy {

	private String itemName;
	private Integer itemQuantity;
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
	
	
}

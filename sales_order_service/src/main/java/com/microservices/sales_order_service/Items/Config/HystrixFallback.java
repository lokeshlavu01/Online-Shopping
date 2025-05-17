package com.microservices.sales_order_service.Items.Config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.sales_order_service.Items.Model.Items;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HystrixFallback implements ItemServiceProxy {
	
	@Autowired
	ItemServiceProxy itemProxy;
	
	Map<String, Items> cachedItems = new HashMap<>();
	
	@HystrixCommand(fallbackMethod="defaultCachedItems")
	public Items getItem(String name) {
		Items item = itemProxy.getItem(name);
		cachedItems.put(name, item);
		return item;
	}
	
	public Items defaultCachedItems(String name) {
		return cachedItems.get(name);
	}

}

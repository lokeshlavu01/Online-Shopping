package com.microservices.sales_order_service.Items.Config;


import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.microservices.sales_order_service.Items.Model.Items;

@FeignClient(name="item-service")
@RibbonClient(name="item-service")
public interface ItemServiceProxy {
	
	@GetMapping(value = "/Items/{name}")
    public Items getItem(@PathVariable(value="name") String name);
}
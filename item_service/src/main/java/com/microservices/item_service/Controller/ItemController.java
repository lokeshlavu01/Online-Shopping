package com.microservices.item_service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.item_service.Model.Items;
import com.microservices.item_service.Repository.ItemRepository;



@CrossOrigin
@RestController
@RequestMapping(path="/Items")
public class ItemController {
	
	@Autowired
	private Environment environment;

	@Autowired
	private ItemRepository itemRepository;

	
	@PostMapping(path="/add")
	public @ResponseBody String addNewItem (@RequestParam String name
			, @RequestParam String description , @RequestParam double price) {
		

		Items n = new Items();
		n.setName(name);
		n.setDescription(description);
		n.setPrice(price);
		itemRepository.save(n);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Items> getAllItems() {
		
		return itemRepository.findAll();
	
	}
	
	@GetMapping(path="/{name}")
	public @ResponseBody Items getItem(@PathVariable("name") String name) {
		Items item = itemRepository.findByName(name);
		item.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		 return item;
		}
	
 }

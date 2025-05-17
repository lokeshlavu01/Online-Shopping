package com.microservices.item_service.Repository;


import org.springframework.data.repository.CrudRepository;

import com.microservices.item_service.Model.Items;

public interface ItemRepository extends CrudRepository<Items, Integer> {

	Items findByName(String name);
	

}

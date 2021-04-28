package com.jaguarcode.pizza.data;

import org.springframework.data.repository.CrudRepository;

import com.jaguarcode.pizza.Order;

public interface OrderRepository 
	extends CrudRepository<Order, Long> {
}

package com.jaguarcode.pizza.data;

import org.springframework.data.repository.CrudRepository;

import com.jaguarcode.pizza.Pizza;

public interface PizzaRepository 
	extends CrudRepository<Pizza, Long> {
}

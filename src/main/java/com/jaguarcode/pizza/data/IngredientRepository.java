package com.jaguarcode.pizza.data;

import org.springframework.data.repository.CrudRepository;

import com.jaguarcode.pizza.Ingredient;

public interface IngredientRepository 
	extends CrudRepository<Ingredient, String>{
}

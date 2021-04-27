package com.jaguarcode.pizza.data;

import com.jaguarcode.pizza.Ingredient;

public interface IngredientRepository {
	Iterable<Ingredient> findAll();
	Ingredient findById(String id);
	Ingredient save(Ingredient ingredient);
}

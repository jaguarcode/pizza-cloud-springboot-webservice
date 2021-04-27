package com.jaguarcode.pizza.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jaguarcode.pizza.Ingredient;
import com.jaguarcode.pizza.data.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient>{

	private IngredientRepository ingredientRepo;
	
	@Autowired
	public IngredientByIdConverter(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}
	
	@Override
	public Ingredient convert(String id) {
		return ingredientRepo.findById(id);
	}
	
}

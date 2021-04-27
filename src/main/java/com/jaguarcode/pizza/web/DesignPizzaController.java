package com.jaguarcode.pizza.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jaguarcode.pizza.Ingredient;
import com.jaguarcode.pizza.Ingredient.Type;
import com.jaguarcode.pizza.Order;
import com.jaguarcode.pizza.Pizza;
import com.jaguarcode.pizza.data.IngredientRepository;
import com.jaguarcode.pizza.data.PizzaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignPizzaController {
	
	private final IngredientRepository ingredientRepo;
	
	private PizzaRepository pizzaRepo;
	
	@Autowired
	public DesignPizzaController(
			IngredientRepository ingredientRepo, PizzaRepository pizzaRepo) {
		this.ingredientRepo = ingredientRepo;
		this.pizzaRepo = pizzaRepo;
	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
					filterByType(ingredients, type));
		}
		
		model.addAttribute("pizza", new Pizza());
		
		return "design";
	}
	
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "pizza")
	public Pizza pizza() {
		return new Pizza();
	}
	
	@PostMapping
	public String processDesign(
			@Valid Pizza design, 
			Errors errors, @ModelAttribute Order order) {
		if (errors.hasErrors()) return "design";
		
		Pizza saved = pizzaRepo.save(design);
		order.addDesign(saved);
		
		return "redirect:/orders/current";
	}
}

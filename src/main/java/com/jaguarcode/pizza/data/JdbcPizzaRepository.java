package com.jaguarcode.pizza.data;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.jaguarcode.pizza.Ingredient;
import com.jaguarcode.pizza.Pizza;

@Repository
public class JdbcPizzaRepository implements PizzaRepository {
	
	private JdbcTemplate jdbc;
	
	public JdbcPizzaRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public Pizza save(Pizza pizza) {
		long pizzaId = savePizzaInfo(pizza);
		pizza.setId(pizzaId);
		for(Ingredient ingredient : pizza.getIngredients()) {
			saveIngredientToPizza(ingredient, pizzaId);
		}
		return pizza;
	}
	
	private long savePizzaInfo(Pizza pizza) {
		pizza.setCreatedAt(new Date());
		
		PreparedStatementCreator psc = 
				new PreparedStatementCreatorFactory(
						"insert into Pizza (name, createdAt) values (?, ?)",
						Types.VARCHAR, Types.TIMESTAMP).newPreparedStatementCreator(
								Arrays.asList(pizza.getName(),
										new Timestamp(pizza.getCreatedAt().getTime()))
						);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbc.update(psc, keyHolder);
		
		return keyHolder.getKey().longValue();
	}
	
	private void saveIngredientToPizza(Ingredient ingredient, long pizzaId) {
		jdbc.update(
				"insert into Pizza_Ingredients (pizza, ingredient) " +
				"values (?, ?)",
				pizzaId, ingredient.getId()
			);
	}
}

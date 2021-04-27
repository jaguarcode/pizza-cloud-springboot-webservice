package com.jaguarcode.pizza.data;

import com.jaguarcode.pizza.Order;

public interface OrderRepository {
	Order save(Order order);
}

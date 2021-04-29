package com.jaguarcode.pizza.data;

import org.springframework.data.repository.CrudRepository;

import com.jaguarcode.pizza.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);
}

package com.exittest.productsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exittest.productsearch.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	public User findByUsername(String username);
	
	public User findByUsernameAndPassword(String username,String password);
}

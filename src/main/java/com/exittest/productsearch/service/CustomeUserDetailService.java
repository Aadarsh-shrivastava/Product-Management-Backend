package com.exittest.productsearch.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exittest.productsearch.entity.CustomUserDetails;
import com.exittest.productsearch.entity.User;
import com.exittest.productsearch.repository.UserRepository;

@Service
public class CustomeUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		final User user = this.repo.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		} else {
			return new CustomUserDetails(user);
		}

		// if(username.equals("aadarsh")) {
//			return new User("aadarsh","123",new ArrayList<>());
//		}
//		else {
//			throw new UsernameNotFoundException("user not found");
//		}

	}

}

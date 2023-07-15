package com.exittest.productsearch.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exittest.productsearch.entity.User;
import com.exittest.productsearch.repository.UserRepository;


@RestController
@CrossOrigin(origins="*")
@RequestMapping("/users/")
public class UserController {

	@Autowired
	UserRepository repo;

	@GetMapping(path = "")
	public List<User> getallUsers() {
		return repo.findAll();

	}
	
	@GetMapping(path="getUsersCount")
	public int getalluserscount() {
		
		 int count=repo.findAll().size();
		 
		 return count;
		
	}

	@PostMapping(path = "", consumes = { "application/json" })
	public  ResponseEntity<User> addUser(@RequestBody User User) {
		User existingUser=repo.findByUsername(User.getUsername());
//		
//			{}
		if(existingUser==null) {
			repo.save(User);
			return ResponseEntity.ok(User);
		}
		else{return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();}

	}

	@PutMapping(path = "", consumes = { "application/json" })
	public User	 updateUser(@RequestBody User User) {
		repo.save(User);
		return User;
	}

	@PostMapping(path = "findByUsernameAndPassword", consumes = { "application/json" })
	public User	 findByUsernameAndPassword(@RequestBody User user) {
		return repo.findByUsernameAndPassword(user.getUsername() , user.getPassword());
		 
	}
	
	@RequestMapping(path = "{UserId}")
	public Optional<User> searchUserById(@PathVariable("UserId") int UserId) {
		return repo.findById(UserId);

	}
	

	@DeleteMapping(path = "{UserId}")
	public void deleteUserById(@PathVariable("UserId") int UserId) {

		repo.deleteById(UserId);
	}

}

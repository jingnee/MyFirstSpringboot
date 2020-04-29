package com.jineey.myspringboot.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.jineey.myspringboot.entity.User;
import com.jineey.myspringboot.entity.Users;
import com.jineey.myspringboot.exception.ResourceNotFoundException;
import com.jineey.myspringboot.repository.UserRepository;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserRestController {
	@Autowired
	private UserRepository repository;
	
	@PostMapping("/users")
	public User create(@RequestBody User user) {
		return repository.save(user);
	}
	
	@GetMapping(value = "/users", produces = {"application/json"})
	public List<User> getUsers(){
		return repository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable Long id) {
		Optional<User> userOpt = repository.findById(id);
		User user = userOpt.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return user;
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User userDetail) {
		User user = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id", id));
		user.setName(userDetail.getName());
		user.setEmail(userDetail.getEmail());
		User updateUser = repository.save(user);
		return updateUser;
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		User user = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "Id", id));
		repository.delete(user);
		return new ResponseEntity<String>(user.getId() + "deleted", HttpStatus.OK);
	}
	
	@GetMapping(value="/usersxml", produces = {"application/xml"})
	public Users getUsersXml(){
		Users users = new Users();
		users.setUsers(repository.findAll());
		return users;
	}
}

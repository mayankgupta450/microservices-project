package com.assignment.userservice.controller;

import com.assignment.userservice.entity.User;
import com.assignment.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository repo;

	@PostMapping
	public User create(@RequestBody User user) {
		return repo.save(user);
	}

	@GetMapping
	public List<User> getAll() {
		return repo.findAll();
	}

	@GetMapping("/{id}")
	public User getById(@PathVariable(name = "id") Long id) {
		return repo.findById(id).orElse(null);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody User updatedUser) {
		User existingUser = repo.findById(id).orElse(null);

		if (existingUser == null) {
			return ResponseEntity.status(404).body("User not found");
		}

		existingUser.setName(updatedUser.getName());
		existingUser.setEmail(updatedUser.getEmail());
		User saved = repo.save(existingUser);
		return ResponseEntity.ok(saved);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(name = "id") Long id) {
		repo.deleteById(id);
	}
}

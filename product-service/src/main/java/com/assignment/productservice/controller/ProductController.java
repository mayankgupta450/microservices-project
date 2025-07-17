package com.assignment.productservice.controller;

import com.assignment.productservice.entity.Product;
import com.assignment.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository repo;

	@PostMapping
	public Product create(@RequestBody Product product) {
		return repo.save(product);
	}

	@GetMapping
	public List<Product> getAll() {
		return repo.findAll();
	}

	@GetMapping("/{id}")
	public Product getById(@PathVariable(name = "id") String id) {
		return repo.findById(id).orElse(null);
	}

	@PutMapping("/{id}")
	public Product update(@PathVariable(name = "id") String id, @RequestBody Product product) {
		product.setId(id);
		return repo.save(product);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(name = "id") String id) {
		repo.deleteById(id);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<Product> getByName(@PathVariable(name="name") String name) {
	    return repo.findByName(name)
	        .map(ResponseEntity::ok)
	        .orElse(ResponseEntity.notFound().build()); // ✅ return proper 404
	}


}

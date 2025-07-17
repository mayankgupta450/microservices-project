package com.assignment.productservice.repository;

import com.assignment.productservice.entity.Product;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
	Optional<Product> findByName(String name);

}


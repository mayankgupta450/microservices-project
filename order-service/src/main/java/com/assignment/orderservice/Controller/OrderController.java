package com.assignment.orderservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.assignment.orderservice.Entity.Order;
import com.assignment.orderservice.Repository.OrderRepository;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private RestTemplate restTemplate;
    
    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody Order order) {
        // Validate user
        try {
            restTemplate.getForEntity(
                "http://localhost:8081/users/" + order.getUserId(),
                Object.class
            );
        } catch (HttpClientErrorException.NotFound ex) {
            return ResponseEntity.status(404).body("User not found");
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("User service error" + ex.getMessage());
        }

        // Validate product
        try {
            restTemplate.getForEntity(
                "http://localhost:8082/products/name/" + order.getProductName(),
                Object.class
            );
        } catch (HttpClientErrorException.NotFound ex) {
            return ResponseEntity.status(404).body("Product not found");
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Product service error" + ex.getMessage());
        }

        //  Save order if both true
        Order saved = repo.save(order);
        return ResponseEntity.ok(saved);
    }

 
    @GetMapping
    public List<Order> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}

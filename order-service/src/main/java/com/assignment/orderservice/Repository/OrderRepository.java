package com.assignment.orderservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.orderservice.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

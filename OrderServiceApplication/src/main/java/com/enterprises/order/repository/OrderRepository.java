package com.enterprises.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enterprises.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	
}

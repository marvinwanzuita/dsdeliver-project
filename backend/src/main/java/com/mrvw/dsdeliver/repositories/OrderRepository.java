package com.mrvw.dsdeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mrvw.dsdeliver.entities.Order;
import com.mrvw.dsdeliver.entities.Product;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	

}

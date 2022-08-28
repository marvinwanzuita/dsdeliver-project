package com.mrvw.dsdeliver.config.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mrvw.dsdeliver.dto.OrderDTO;
import com.mrvw.dsdeliver.dto.ProductDTO;
import com.mrvw.dsdeliver.entities.Order;
import com.mrvw.dsdeliver.entities.OrderStatus;
import com.mrvw.dsdeliver.entities.Product;
import com.mrvw.dsdeliver.repositories.OrderRepository;
import com.mrvw.dsdeliver.repositories.ProductRepository;

import net.bytebuddy.implementation.bind.annotation.Super.Instantiation;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findOrdersWithProducts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(),
				Instant.now(), OrderStatus.PENDING);
		for (ProductDTO p : dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		order = repository.save(order);
		return new OrderDTO(order);
	}
	
}

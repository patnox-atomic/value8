package com.patnox.supermarket.sales;

import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.*;
import java.time.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Service
public class SaleService 
{
	private final OrderRepository orderRepository;

	  @Autowired
	  public SaleService(OrderRepository orderRepository) {
	    this.orderRepository = orderRepository;
	  }
	  
	public List<Order> getAllOrders() 
	{
	    return orderRepository.findAll();
	}
	
	public void addNewOrder(Order newOrder)
	{
		System.out.println("My New Order: " + newOrder);
		orderRepository.save(newOrder);
	}
	
	@Transactional
	public void deleteOrder(Long orderId)
	{
		System.out.println("Request to delete Order ID: " + orderId);
		boolean exists = orderRepository.existsById(orderId);
		if(!exists)
		{
			System.err.println("Error: Order with ID: " + orderId + " does not exist");
			throw new IllegalStateException("Order with ID: " + orderId + " does not exist");
		}
		else
		{
			System.out.println("Order with ID: " + orderId + " exists so we will proceed");
			Order victimizedOrder = orderRepository.findById(orderId).orElseThrow(() -> new IllegalStateException("Order with ID: " + orderId + " does not exist"));
			victimizedOrder.setIs_deleted(true);
		}
	}
	
	@Transactional
	public void updateOrder(Long orderId, Long product_id, Long quantity, Boolean is_fullfilled, String date_ordered, String date_fullfilled, Boolean is_deleted)
	{
		System.out.println("Request to update Order ID: " + orderId);
		boolean exists = orderRepository.existsById(orderId);
		if(!exists)
		{
			System.err.println("Error: Order with ID: " + orderId + " does not exist");
			throw new IllegalStateException("Order with ID: " + orderId + " does not exist");
		}
		else
		{
			System.out.println("Order with ID: " + orderId + " exists so we will proceed");
			Order victimizedOrder = orderRepository.findById(orderId).orElseThrow(() -> new IllegalStateException("Order with ID: " + orderId + " does not exist"));
			if(product_id != null && product_id != 0) victimizedOrder.setProduct_id(product_id);
			if(quantity != null && quantity != 0) victimizedOrder.setQuantity(quantity);
			if(is_fullfilled != null) victimizedOrder.setIs_fullfilled(is_fullfilled);
			if(product_id != null && date_ordered.length() > 0) victimizedOrder.setDate_ordered(LocalDate.parse(date_ordered, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			if(date_fullfilled != null && date_fullfilled.length() > 0) victimizedOrder.setDate_fullfilled(LocalDate.parse(date_fullfilled, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			if(is_deleted != null) victimizedOrder.setIs_deleted(is_deleted);
		}
	}
}

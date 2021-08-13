package com.patnox.supermarket.products;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.time.*;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
	
	private final ProductService productService;
		
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public List<Product> getAll() {
		return productService.getAllProducts();
	}
	
	@PostMapping
	public void createNewOrder(@RequestBody Product newProduct)
	{
		productService.addNewProduct(newProduct);
	}
	
	@DeleteMapping(path = "{orderId}")
	public void deleteProduct(@PathVariable("orderId") Long orderId)
	{
		productService.deleteProduct(orderId);
	}
	
	@PutMapping(path = "{orderId}")
	public void updateProduct(@PathVariable("orderId") Long orderId,
				@RequestParam(required = false) Long product_id,
				@RequestParam(required = false) Long quantity,
				@RequestParam(required = false) Boolean is_fullfilled,
				@RequestParam(required = false) String date_ordered,
				@RequestParam(required = false) String date_fullfilled,
				@RequestParam(required = false) Boolean is_deleted
			)
	{
		productService.updateProduct(orderId, product_id, quantity, is_fullfilled, date_ordered, date_fullfilled, is_deleted);
	}
	
}

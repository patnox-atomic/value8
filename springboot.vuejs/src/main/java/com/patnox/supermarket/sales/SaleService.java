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
import com.patnox.supermarket.products.*;

@Service
public class SaleService 
{
	private final SaleRepository saleRepository;
	private final ProductRepository productRepository;

	  @Autowired
	  public SaleService(SaleRepository saleRepository, ProductRepository productRepository) 
	  {
	    this.saleRepository = saleRepository;
	    this.productRepository = productRepository;
	  }
	  
	public List<Sale> getAllSales() 
	{
	    return saleRepository.findAll();
	}
	
	public void addNewSale(Sale newSale)
	{
		System.out.println("My New Sale: " + newSale);
		saleRepository.save(newSale);
	}
	
	@Transactional
	public void deleteSale(Long saleId)
	{
		System.out.println("Request to delete Sale ID: " + saleId);
		boolean exists = saleRepository.existsById(saleId);
		if(!exists)
		{
			System.err.println("Error: Sale with ID: " + saleId + " does not exist");
			throw new IllegalStateException("Sale with ID: " + saleId + " does not exist");
		}
		else
		{
			System.out.println("Sale with ID: " + saleId + " exists so we will proceed");
			Sale victimizedSale = saleRepository.findById(saleId).orElseThrow(() -> new IllegalStateException("Sale with ID: " + saleId + " does not exist"));
			victimizedSale.setIs_deleted(true);
		}
	}
	
	@Transactional
	public void updateSale(Long saleId, Long product_id, Long quantity, Double price, String sale_date, Boolean is_deleted)
	{
		System.out.println("Request to update Sale ID: " + saleId);
		boolean exists = saleRepository.existsById(saleId);
		if(!exists)
		{
			System.err.println("Error: Sale with ID: " + saleId + " does not exist");
			throw new IllegalStateException("Sale with ID: " + saleId + " does not exist");
		}
		else
		{
			System.out.println("Sale with ID: " + saleId + " exists so we will proceed");
			Sale victimizedSale = saleRepository.findById(saleId).orElseThrow(() -> new IllegalStateException("Sale with ID: " + saleId + " does not exist"));
			Product victimizedProduct = productRepository.findById(product_id).orElseThrow(() -> new IllegalStateException("Product with ID: " + product_id + " does not exist"));
			if(product_id != null && product_id != 0) victimizedSale.setProduct(victimizedProduct);
			if(quantity != null && quantity != 0) victimizedSale.setQuantity(quantity);
			if(price != null && price != 0) victimizedSale.setPrice(price);
			if(sale_date != null && sale_date.length() > 0) victimizedSale.setSale_date(LocalDate.parse(sale_date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			if(is_deleted != null) victimizedSale.setIs_deleted(is_deleted);
		}
	}
}

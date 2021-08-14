package com.patnox.supermarket;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import java.time.*;
import java.util.*;
import com.patnox.supermarket.orders.*;
import com.patnox.supermarket.products.*;
import com.patnox.supermarket.sales.*;

@Configuration
public class DataConfig 
{
	
	@Bean
	CommandLineRunner orderDataInjector(OrderRepository repository)
	{
		return args -> {
			Order ord1 = new Order(
					64L,
					500L,
					false,
					LocalDate.of(2000, Month.JANUARY, 5),
					LocalDate.of(2001, Month.DECEMBER, 15),
					false
			);
			Order ord2 = new Order(
					34L,
					670L,
					false,
					LocalDate.of(2010, Month.JULY, 16),
					LocalDate.of(2012, Month.NOVEMBER, 25),
					false
			);
			repository.saveAll(
					List.of(ord1, ord2)
			);
		};
	}
	
	@Bean
	CommandLineRunner productDataInjector(ProductRepository repository)
	{
		return args -> {
			Product ord1 = new Product(
					"SuperLoaf",
					"Best Bread Ever",
					"37827719384",
					732.00D,
					500L,
					30L,
					600L,
					false
			);
			Product ord2 = new Product(
					"Broadways",
					"Ancient Bread",
					"91002384665",
					236.00D,
					650L,
					60L,
					900L,
					false
			);
			repository.saveAll(
					List.of(ord1, ord2)
			);
		};
	}
	
	@Bean
	CommandLineRunner saleDataInjector(SaleRepository repository)
	{
		return args -> {
			Sale ord1 = new Sale(
					1L,
					5L,
					5000.00D,
					LocalDate.of(2001, Month.DECEMBER, 15),
					false
			);
			Sale ord2 = new Sale(
					2L,
					2L,
					650.00D,
					LocalDate.of(2012, Month.NOVEMBER, 25),
					false
			);
			repository.saveAll(
					List.of(ord1, ord2)
			);
		};
	}
	
}

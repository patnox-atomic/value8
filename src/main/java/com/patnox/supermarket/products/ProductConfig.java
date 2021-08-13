package com.patnox.supermarket.products;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import java.time.*;
import java.util.*;

@Configuration
public class ProductConfig 
{
	
	@Bean
	CommandLineRunner commandLineRunner(OrderRepository repository)
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
	
}

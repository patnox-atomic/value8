package com.patnox.supermarket;

import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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
    public JavaMailSender javaMailSender() { 
          //return new JavaMailSender();
          return new JavaMailSenderImpl();
    }
	
	@Bean
	CommandLineRunner productDataInjector(ProductRepository productRepository, OrderRepository orderRepository, SaleRepository saleRepository)
	{
		return args -> {
			Product prd1 = new Product(
					"SuperLoaf",
					"Best Bread Ever",
					"37827719384",
					732.00D,
					500L,
					30L,
					600L,
					false
			);
			Product prd2 = new Product(
					"Broadways",
					"Ancient Bread",
					"91002384665",
					236.00D,
					650L,
					60L,
					900L,
					false
			);
			productRepository.saveAll(
					List.of(prd1, prd2)
			);
//			Product prdOne = productRepository.findById(1L).orElseThrow(() -> new IllegalStateException("Product with ID: 1 does not exist"));
//			System.out.println("Found Product One: " + prdOne.getName());
//			Product prdTwo = productRepository.findById(2L).orElseThrow(() -> new IllegalStateException("Product with ID: 1 does not exist"));
//			System.out.println("Found Product Two: " + prdTwo.getName());
			Order ord1 = new Order(
					prd1,
					500L,
					false,
					LocalDate.of(2000, Month.JANUARY, 5),
					LocalDate.of(2001, Month.DECEMBER, 15),
					false
			);
			Order ord2 = new Order(
					prd2,
					670L,
					false,
					LocalDate.of(2010, Month.JULY, 16),
					LocalDate.of(2012, Month.NOVEMBER, 25),
					false
			);
			orderRepository.saveAll(
					List.of(ord1, ord2)
			);
			Sale sal1 = new Sale(
					prd1,
					5L,
					5000.00D,
					LocalDate.of(2001, Month.DECEMBER, 15),
					false
			);
			Sale sal2 = new Sale(
					prd2,
					2L,
					650.00D,
					LocalDate.of(2012, Month.NOVEMBER, 25),
					false
			);
			saleRepository.saveAll(
					List.of(sal1, sal2)
			);
		};
	}
	
}

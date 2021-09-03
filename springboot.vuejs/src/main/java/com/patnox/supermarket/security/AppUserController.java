package com.patnox.supermarket.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patnox.supermarket.products.Product;
import com.patnox.supermarket.products.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.time.*;

@RestController
@RequestMapping(path = "api/v1/user")
public class AppUserController 
{
	private final AppUserService appUserService;
	
	@Autowired
	public AppUserController(AppUserService appUserService) {
		this.appUserService = appUserService;
	}

	@GetMapping
	public List<AppUser> getAll() {
		return appUserService.getAllAppUsers();
	}
}

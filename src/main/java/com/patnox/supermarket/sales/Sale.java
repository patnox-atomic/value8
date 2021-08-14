package com.patnox.supermarket.sales;

import javax.persistence.*;
import java.util.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.*;

import java.time.*;
import org.apache.commons.lang3.builder.*;

@DynamicInsert
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "sales")
@Table(name = "sales")
public class Sale 
{
	  @Id
	  @Column(name = "id", unique = true, nullable = false)
	  @SequenceGenerator(
		  name = "sale_sequence",
		  sequenceName = "sale_sequence",
		  allocationSize = 1
	  )
	  @GeneratedValue(
			  strategy = GenerationType.SEQUENCE,
			  generator = "sale_sequence"
	  )
	  private Long id;
	  
	  @NotNull
	  @Column(name = "product_id")
	  private Long product_id;
	  
	  @NotNull
	  @Column(name = "quantity")
	  private Long quantity;
	
	  @NotNull
	  @Min(1)
	  @Column(name = "price")
	  private Double price;
	  
	  @Column(name = "sale_date", columnDefinition="DATE DEFAULT CURRENT_DATE")
	  @JsonFormat(pattern="yyyy-MM-dd", timezone="Africa/Nairobi")
	  //@Temporal(TemporalType.DATE)
	  private LocalDate sale_date;
	  
	  @Column(name = "is_deleted" ,columnDefinition = "boolean default false")
	  private Boolean is_deleted;
	  
	  public Sale() {}  

	public Sale(Long id, @NotNull Long product_id, @NotNull Long quantity, @NotNull @Min(1) Double price,
			LocalDate sale_date, Boolean is_deleted) {
		super();
		this.id = id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.price = price;
		this.sale_date = sale_date;
		this.is_deleted = is_deleted;
	}

	public Sale(@NotNull Long product_id, @NotNull Long quantity, @NotNull @Min(1) Double price, LocalDate sale_date,
			Boolean is_deleted) {
		super();
		this.product_id = product_id;
		this.quantity = quantity;
		this.price = price;
		this.sale_date = sale_date;
		this.is_deleted = is_deleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LocalDate getSale_date() {
		return sale_date;
	}

	public void setSale_date(LocalDate sale_date) {
		this.sale_date = sale_date;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String toString() 
	{
		   return ToStringBuilder.reflectionToString(this);
	}
		
}

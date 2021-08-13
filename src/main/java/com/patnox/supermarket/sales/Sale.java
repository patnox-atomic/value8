package com.patnox.supermarket.sales;

import javax.persistence.*;
import java.util.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;

import java.time.*;
import org.apache.commons.lang3.builder.*;

@DynamicInsert
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "orders")
@Table(name = "orders")
public class Sale 
{
	  @Id
	  @Column(name = "id", unique = true, nullable = false)
	  @SequenceGenerator(
		  name = "order_sequence",
		  sequenceName = "order_sequence",
		  allocationSize = 1
	  )
	  @GeneratedValue(
			  strategy = GenerationType.SEQUENCE,
			  generator = "order_sequence"
	  )
	  private Long id;
	  
	  //@NotBlank
	  @NotNull
	  @Column(name = "product_id")
	  private Long product_id;
	
	  @NotNull
	  @Min(1)
	  @Column(name = "quantity")
	  private Long quantity;
	  
	  @Column(name = "is_fullfilled" ,columnDefinition = "boolean default false")
	  private Boolean is_fullfilled;
	
	  //@NotBlank
	  @Column(name = "date_ordered")
	  private LocalDate date_ordered;
	  
	  //@NotBlank
	  @Column(name = "date_fullfilled")
	  private LocalDate date_fullfilled;
	  
	  @Column(name = "is_deleted" ,columnDefinition = "boolean default false")
	  private Boolean is_deleted;
	  
	  public Sale() {}
	
	  public Sale(Long id, Long product_id, Long quantity, Boolean is_fullfilled, LocalDate date_ordered, LocalDate date_fullfilled, Boolean is_deleted) 
	  {
		    this.id = id;
		    this.product_id = product_id;
		    this.quantity = quantity;
		    this.is_fullfilled = is_fullfilled;
		    this.date_ordered = date_ordered;
		    this.date_fullfilled = date_fullfilled;
		    this.is_deleted = is_deleted;
	  }
	  
	  public Sale(Long product_id, Long quantity, Boolean is_fullfilled, LocalDate date_ordered, LocalDate date_fullfilled, Boolean is_deleted) 
	  {
		    this.product_id = product_id;
		    this.quantity = quantity;
		    this.is_fullfilled = is_fullfilled;
		    this.date_ordered = date_ordered;
		    this.date_fullfilled = date_fullfilled;
		    this.is_deleted = is_deleted;
	  }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Boolean getIs_fullfilled() {
		return is_fullfilled;
	}

	public void setIs_fullfilled(Boolean is_fullfilled) {
		this.is_fullfilled = is_fullfilled;
	}

	public LocalDate getDate_ordered() {
		return date_ordered;
	}

	public void setDate_ordered(LocalDate date_ordered) {
		this.date_ordered = date_ordered;
	}

	public LocalDate getDate_fullfilled() {
		return date_fullfilled;
	}

	public void setDate_fullfilled(LocalDate date_fullfilled) {
		this.date_fullfilled = date_fullfilled;
	}
	
	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String toString() 
	{
		   return ToStringBuilder.reflectionToString(this);
	}
		
}

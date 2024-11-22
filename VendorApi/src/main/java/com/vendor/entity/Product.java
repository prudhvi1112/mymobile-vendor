package com.vendor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "product_service")
@Data
public class Product {

	@Id
	@Size(min = 3, max = 35, message = "Username must be between 3 and 50 characters")
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$", message = "User ID must be alphanumeric and contain at least one letter and one digit.")
	private String productId;

	@NotNull
	@Size(min = 1, max = 500)
	private String description;

	@NotNull
	private String brand;

	@NotNull
	@Positive
	private Double price;

	@NotNull
	@Positive
	private Integer quantity;

	@NotNull
	private String color;

	@NotNull
	private String model;

	@Lob
	private byte[] imageOfProduct;

	@Size(min = 1, max = 1000)
	private String productFeatures;

	@NotNull
	private String userId;

}

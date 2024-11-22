package com.vendor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vendor.dto.ProductAddedResponse;
import com.vendor.dto.ProductDto;
import com.vendor.dto.ProductsDto;
import com.vendor.dto.UserDataDto;
import com.vendor.entity.Product;
import com.vendor.exception.InvaildUserException;
import com.vendor.exception.InvaildUserToAddProductException;
import com.vendor.exception.ProductAlreadyExistsException;
import com.vendor.exception.UserNotFoundException;
import com.vendor.feignclient.UserRegisterClient;
import com.vendor.repo.ProductDao;

import feign.FeignException;


@Service
public class VendorService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private UserRegisterClient userDetailsDao;

	@Autowired
	private ModelMapper mapper;

	@Transactional
	public ProductAddedResponse createProduct(ProductDto productDto) {

		try {
		UserDataDto userData = userDetailsDao.getUserData(productDto.getVendorId());
		
		System.out.println(userData);
		if (userData.getUserRole().equals("vendor")) {
			Product product = mapper.map(productDto, Product.class);
			Product findProduct = productDao.findById(product.getProductId()).orElse(null);
			if (findProduct == null) {

				    product.setUserId(userData.getUserId());
				    productDao.save(product);
					ProductAddedResponse response = new ProductAddedResponse();
					response.setProductId(product.getProductId());
					response.setSuccessMessage("Product Added Successfully");
					return response;
				}
			 else {
				throw new ProductAlreadyExistsException("Product Already Exists");
			}
		} else {
			throw new InvaildUserToAddProductException("Invalid User To Add Products");
		}
		}
		catch (FeignException.NotFound e) 
		{
			throw new UserNotFoundException("Vendor not found with ID: " + productDto.getVendorId());
			
		}
		
	}

	public List<ProductsDto> getProductsList(String vendorId) {
		if (vendorId == null) {
			List<Product> productList = productDao.findAll();
			return productList.stream().map(product -> mapper.map(product, ProductsDto.class))
					.collect(Collectors.toList());
		} else {
			List<Product> productListByVendoeId = productDao.findByUserId(vendorId);
			if (productListByVendoeId.isEmpty()) {
				throw new InvaildUserException("Invalid user or no products included.");
			}
			return productListByVendoeId.stream().map(product -> mapper.map(product, ProductsDto.class))
					.collect(Collectors.toList());
		}

	}
}

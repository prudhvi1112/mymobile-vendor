package com.vendor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendor.entity.Product;



@Repository
public interface ProductDao extends JpaRepository<Product, String> {

	List<Product> findByUserId(String vendorId);

}

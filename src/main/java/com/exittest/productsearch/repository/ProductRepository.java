package com.exittest.productsearch.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exittest.productsearch.entity.Product;
public interface ProductRepository extends JpaRepository<Product,Integer>{
	
	public List<Product> findByBrandName(String brandName);
	
	public List<Product> findByProductName(String name);
	
	public List<Product> findByCode(String code);
	
	public List<Product> findByProductNameContains(String code);
	
	public Product findByProductId(int productId);
}

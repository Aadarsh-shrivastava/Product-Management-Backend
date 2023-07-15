package com.exittest.productsearch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exittest.productsearch.entity.Product;
import com.exittest.productsearch.entity.Review;
import com.exittest.productsearch.repository.ProductRepository;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/products/")
public class ProductController {

	@Autowired
	ProductRepository repo;
	@CrossOrigin(origins = "*")
	@GetMapping(path = "")
	public List<Product> getallproducts() {

		return repo.findAll();

	}

	@GetMapping(path = "getProductsCount")
	public int getallproductscount() {

		int count = repo.findAll().size();

		return count;

	}

	@PostMapping(path = "", consumes = { "application/json" })
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product productNew=null;
		try {
			productNew=repo.save(product);
			return ResponseEntity.ok(productNew);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		

	}

	@PutMapping(path = "", consumes = { "application/json" })
	public void updateProduct(@RequestBody Product Product) {
		repo.save(Product);
	}

	@RequestMapping(path = "{productId}")
	public ResponseEntity<Product> searchProductById(@PathVariable("productId") int productId) {
		Optional<Product> product = repo.findById(productId);
		if (product == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(product);

	}

	@DeleteMapping(path = "{productId}")
	public void deleteProductById(@PathVariable("productId") int productId) {

		repo.deleteById(productId);
	}

	@RequestMapping(path = "brandName/{brandName}")
	public List<Product> searchProductByBrand(@PathVariable("brandName") String brandName) {
		return repo.findByBrandName(brandName);

	}

	@GetMapping(path = "getAverageRating/{productId}")
	public int getAverageRating(@PathVariable("productId") int productId) {
		int sum = 0;
		Product product = repo.findByProductId(productId);
		List<Review> reviews = product.getReview();
		for (Review review : reviews) {
			if (review.getStatus().equalsIgnoreCase("Approved"))
				sum += review.getRating();
		}

		int count = reviews.size();

		return (sum / count);

	}

	@RequestMapping(path = "name/{name}")
	public List<Product> searchProductByName(@PathVariable("name") String name) {
		return repo.findByProductName(name);

	}

	@RequestMapping(path = "productCode/{code}")
	public List<Product> searchProductByCode(@PathVariable("code") String code) {
		return repo.findByCode(code);

	}

	@RequestMapping(path = "nameLike/{name}")
	public List<Product> findByProductName(@PathVariable("name") String name) {
		return repo.findByProductNameContains(name);

	}

	@RequestMapping(path = "searchReviewByProductId/{productId}")
	public List<Review> searchReviewByProductId(@PathVariable("productId") int productId) {
		Product product = repo.findById(productId).get();
		return product.getReview();

	}

}

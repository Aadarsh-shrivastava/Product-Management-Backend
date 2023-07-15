package com.exittest.productsearch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;	
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exittest.productsearch.entity.Review;
import com.exittest.productsearch.repository.ReviewRepository;




@RestController
@CrossOrigin(origins="*")
@RequestMapping("/reviews/")
public class ReviewController {
	
	@Autowired
	ReviewRepository repo;
	
	@GetMapping(path="")
	public List<Review> getallreviews() {
		return repo.findAll();
		
	}
	
	@GetMapping(path="getReviewsCount")
	public int getallreviewsscount() {
		
		 int count=repo.findAll().size();
		 
		 return count;
		
	}
	
	@GetMapping(path="getAverageRating")
	public int getAverageRating() {
		int sum=0;

		List<Review> reviews=repo.findAll();
		for(Review review:reviews) {
			if(review.getStatus().equalsIgnoreCase("Approved"))
				sum+=review.getRating();}
		
		 int count=repo.findAll().size();
		 
		 
		 return (sum/count);
		
	}
	@PostMapping(path="",consumes= {"application/json"})
	public Review addReview(@RequestBody Review review) 
	{	
		repo.save(review);
		return review;
		
	}
	
	

	@GetMapping(path="getProductByReview/{reviewId}")
	public int getProductsByReview(@PathVariable("reviewId") int reviewId) {
		Review review=repo.getById(reviewId);
		return 0;
		
	}
	
	@PutMapping(path="",consumes= {"application/json"})
	public void updateReview(@RequestBody Review Review) {
		repo.save(Review);
	}
	
	@RequestMapping(path="{reviewId}")
	public Optional<Review> searchReviewById(@PathVariable("reviewId") int reviewId) {
		return repo.findById(reviewId);
		
	}
	
	@RequestMapping(path="status/{status}")
	public List<Review> searchReviewByStatus(@PathVariable("status") String status) {
		return repo.findByStatus(status);
		
	}
	
	@DeleteMapping(path="{reviewId}")
	public void deleteReviewById(@PathVariable("reviewId") int reviewId) {
		
		repo.deleteById(reviewId);
	}
	
	@RequestMapping(path="approveReview/{reviewId}")
	public Optional<Review> approveReview(@PathVariable("reviewId") int reviewId) {
		 repo.approveReview(reviewId);
		return repo.findById(reviewId);
		
	}
	
	
	
}	

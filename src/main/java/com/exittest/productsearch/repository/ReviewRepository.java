package com.exittest.productsearch.repository;

import java.util.List;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exittest.productsearch.entity.Review;


public interface ReviewRepository extends JpaRepository<Review,Integer>{
	
	@Modifying
	@Transactional
	@Query("Update Review set status ='Approved' where reviewId=:reviewId")
	void approveReview(@Param("reviewId") int reviewId);
	
	
	List<Review> findByStatus(String status);
	
	
	
	
}

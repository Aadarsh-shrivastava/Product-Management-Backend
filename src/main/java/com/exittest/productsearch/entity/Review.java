package com.exittest.productsearch.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="review_id")
	private int reviewId;
	private String review;
	private int rating;
	private String status;
	private String userName;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="product_id")
	private Product product;

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Review(int reviewId, String review, int rating, String status, String userName, Product product) {
		super();
		this.reviewId = reviewId;
		this.review = review;
		this.rating = rating;
		this.status = status;
		this.userName = userName;
		this.product = product;
	}


	
	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "ProductReview [reviewId=" + reviewId + ", review=" + review + ", rating=" + rating + ", status="
				+ status + ", product=" + product + "]";
	}
	
	
}
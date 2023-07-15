package com.exittest.productsearch.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exittest.productsearch.entity.Product;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)
public class HomeController {
	@GetMapping("/")
	@ResponseBody
	public String home() {
		return "<marquee> <h1 style=\"color:red;\">working</h1></marquee>";
		
	}
	
}

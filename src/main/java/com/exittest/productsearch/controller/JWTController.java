package com.exittest.productsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exittest.productsearch.entity.JWTRequest;
import com.exittest.productsearch.entity.JWTResponse;
import com.exittest.productsearch.service.CustomeUserDetailService;
import com.exittest.productsearch.utils.JWTUtil;

@RestController
@CrossOrigin(origins = "*")
public class JWTController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomeUserDetailService customUserDetailService;

	@Autowired
	private JWTUtil jwtUtil;

	@PostMapping(value = "/token")
	public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtRequest) throws Exception {
		System.out.println(jwtRequest);

		try {
				
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()) );
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Bad credentials");
		}
		
		UserDetails userDetails=this.customUserDetailService.loadUserByUsername(jwtRequest.getUsername());
		
		String token=this.jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JWTResponse(token));
	}
}

package com.vijayforvictory.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vijayforvictory.MyUserDetailsService;
import com.vijayforvictory.models.AuthenticationRequest;
import com.vijayforvictory.models.AuthenticationResponse;
import com.vijayforvictory.utils.JwtUtil;

@RestController
public class HelloResource {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping({ "/hello" })
	public String hello() {
		return "<h1>Hello World</h1>";
	}

	@RequestMapping({ "/admin" })
	public String admin() {
		return "<h1>Hello Admin</h1>";
	}

	@RequestMapping({ "/user" })
	public String user() {
		return "<h1>Hello User</h1>";
	}

	@RequestMapping({ "/" })
	public String home() {
		return "<h1>Spring Boot Demo</h1><br/>This is a demo app to learn Spring Boot + Spring Security + Authentication + Authorisation";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		} catch (BadCredentialsException badCredentialsException) {
			badCredentialsException.printStackTrace();
			throw new Exception("Incorrect user credentials ", badCredentialsException);
		}
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
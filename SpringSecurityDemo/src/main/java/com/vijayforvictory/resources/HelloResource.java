package com.vijayforvictory.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

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

}

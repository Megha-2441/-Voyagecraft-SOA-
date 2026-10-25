package com.klu.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klu.entity.User;
import com.klu.service.UserService;

@RestController
@RequestMapping("/ms1")
public class UserController {

	@Autowired
	 UserService service;

	 @PostMapping("/signin")
		public Object signin(@RequestBody Map<String, String> u1) {
			return service.signinService(u1);
		}
		
		@PostMapping("/signup")
		public Object signup(@RequestBody User u1) {
			return service.signupService(u1);
		}
		
		@GetMapping("/getUser/{id}")
		public User getUserById(@PathVariable("id") Long id) {
		    return service.getUserById(id);
		  }
			
		
}

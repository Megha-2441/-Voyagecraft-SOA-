package com.klu.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.UserServiceApplication;
import com.klu.entity.User;
import com.klu.repo.UserRepo;

@Service
public class UserService {
	private final UserServiceApplication userserviceApplication;
	@Autowired
	private UserRepo repo;
	@Autowired
	private JwtService jwtservice;

	UserService(UserServiceApplication userserviceApplication) {
		this.userserviceApplication = userserviceApplication;
	}

	public Object signupService(User u1) {
		Map<String, Object> response = new HashMap<>();

		try {

			User user = repo.findByUsername(u1.getUsername());

			if (user != null) {
				response.put("code", 501);
				response.put("message", "user already exist");
			} else {
				u1.setRole(1);
				repo.save(u1);

				response.put("code", 200);
				response.put("message", "User Registerd Successfully");
			}

			return response;
		} catch (Exception e) {
			response.put("code", 500);
			response.put("message", e.getMessage());
			return response;
		}

	}

	public Object signinService(Map<String, String> u1) {
		Map<String, Object> response = new HashMap<>();

		try {
			User user = repo.findByUsername(u1.get("username"));

			if (user == null || !user.getPassword().equals(u1.get("password"))) {
				response.put("code", "501");
				response.put("message", "Authentication Failed");
			} else {
				response.put("code", 200);
				response.put("jwt", jwtservice.generateJWT(u1, user.getRole().toString(), user.getId()));
			}

			return response;
		} catch (Exception e) {
			response.put("code", 500);
			response.put("message", e.getMessage());
			return response;
		}
	}

	public User getUserById(Long id) {
		User exists = repo.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
		return exists;

	}

}

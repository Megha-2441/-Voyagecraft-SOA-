package com.klu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klu.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	 //fetch record from db based on username which is non-primary key
		User findByUsername(String username);
}

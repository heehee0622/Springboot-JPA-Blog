package com.syshology.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syshology.blog.model.User;

//자동 빈 등록
public interface UserRepository  extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);
}

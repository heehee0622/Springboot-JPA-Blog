package com.syshology.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syshology.blog.model.User;
import com.syshology.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	public void 회원가입(User user) throws Exception {
		try {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			user.setPassword(encPassword);
			userRepository.save(user);
		} catch (IllegalArgumentException e) {
			throw e;
		}
	}

	@Transactional
	public void 회원수정(User user) {
		User psersistance = userRepository.findById(user.getId()).orElseThrow(()-> {
		return new IllegalArgumentException();
		});
		if (psersistance.getOauth() == null || psersistance.getOauth().equals("")) {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			psersistance.setPassword(encPassword);
			psersistance.setEmail(user.getEmail());
		}
		
	}
	
	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		User user = userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});
		return user;
	}
	
}

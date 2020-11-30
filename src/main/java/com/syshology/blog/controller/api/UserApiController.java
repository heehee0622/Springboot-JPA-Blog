package com.syshology.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.syshology.blog.dto.ResposeDto;
import com.syshology.blog.model.RoleType;
import com.syshology.blog.model.User;
import com.syshology.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private BCryptPasswordEncoder encodePWD;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	@PostMapping("/auth/joinProc")
	public ResposeDto<Integer> save(@RequestBody User user) throws Exception {
		String rawPasword = user.getPassword();
		String encPassword=  encodePWD.encode(rawPasword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		 userService.회원가입(user);
		return new ResposeDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/user")
	public ResposeDto<Integer> update(@RequestBody User user){
		// model attribute vs requestbody
		userService.회원수정(user);
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResposeDto<Integer> (HttpStatus.OK.value(), 1);
	}
}

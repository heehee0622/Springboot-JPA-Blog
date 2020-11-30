package com.syshology.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.syshology.blog.model.User;
import com.syshology.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{
	//스프링이 로그인 요청을 가로 챌때 username, password 변수 2개를 가로채는데
	// 패스워드 부분처리는 알아서 함
	// 사용자 이름이 DB에 있는지 유무 확인 해주면됨
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 패스워드 확인 부분
		User principal = userRepository.findByUsername(username).get();
		return new PrincipalDetail(principal);// 시큐리티 세션 생성
	}

}

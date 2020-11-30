package com.syshology.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.syshology.blog.model.KakaoProfile;
import com.syshology.blog.model.OAuthToken;
import com.syshology.blog.model.User;
import com.syshology.blog.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Value("{cos.key}")
	private String cosKey;
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "/user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "/user/loginForm";
	}
	
	
	@GetMapping("/user/updateForm")
	public String saveForm() {
		return "user/updateForm";
	}
	
	@GetMapping("/auth/kakao/callback")
	public  String kakaoCallback(String code) throws Exception {
		// 확인을 위해서 하나의 컨트롤러에 모두 작성
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "b950b8e64f68a4ff222ac20e15b48cc7");
		params.add("redirect_uti", "http://localhost:8080/auth/kakao/callback");
		params.add("code", code);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
		ResponseEntity<OAuthToken> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				request,
				OAuthToken.class
		);
		RestTemplate rt2 = new RestTemplate();
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+ response.getBody().getAccess_token());
		headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);
		ResponseEntity<KakaoProfile> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest,
				KakaoProfile.class
		);

		KakaoProfile kakaoProfile = response2.getBody();
		String id = kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId();
		String email = kakaoProfile.getKakao_account().getEmail();
		User kakaoUser = User.builder().username(id).email(email).password(cosKey).oauth("kakao").build();
		User originUser = userService.회원찾기(kakaoUser.getUsername());
		if (originUser.getUsername() == null) {
			System.out.println("기존 회원이  아닙니다.");
			userService.회원가입(kakaoUser);
		}
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), kakaoUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);		
		
		return "redirect:/";
	}
}

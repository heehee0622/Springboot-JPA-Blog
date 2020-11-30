package com.syshology.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.syshology.blog.config.auth.PrincipalDetailService;

@Configuration
@EnableWebSecurity // 시큐리티의 필터가 등록이 된다.
@EnableGlobalMethodSecurity (prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크 하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PrincipalDetailService detialService;
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	// 시큐리티가 대신 로그인을 해줄때  패스워드를 가로채기 하는데 해당 패스워드가 뭘로 해쉬가 되어 회원 가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 Db에 있는 해쉬랑 비교 할 수 있음
  @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detialService).passwordEncoder(encode());// 패스워드 비교를 위해서 해줘야 함
	}
  
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
	  return super.authenticationManagerBean();
  }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
			.authorizeRequests()
			.antMatchers("/","/main", "/auth/**", "/js/**", "/css/**", "/image/**")
			.permitAll()
			.anyRequest()
			.authenticated()
		.and()
		.formLogin()
		.loginPage("/auth/loginForm")
		.loginProcessingUrl("/auth/loginProc")
		.defaultSuccessUrl("/");
		
	}
}

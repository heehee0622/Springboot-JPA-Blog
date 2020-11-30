package com.syshology.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.syshology.blog.model.User;

import lombok.Getter;

@Getter
public class PrincipalDetail implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8485930318249814891L;
	private User user;

	public PrincipalDetail(User user) {
		this.user = user;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}
// 계정 만료
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
//계정 잠겼는지
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
//비밀번호 만료
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
//계정 활성화
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	//계정 권한
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
		collection.add(()->{return "ROLE_"+user.getRole();});
		return collection;
	}
	
}

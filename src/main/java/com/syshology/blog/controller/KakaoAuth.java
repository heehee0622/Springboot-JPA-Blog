package com.syshology.blog.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoAuth {
	private String grant_type;
	private String client_id;
	private String redirect_uri;
	private String code;
}

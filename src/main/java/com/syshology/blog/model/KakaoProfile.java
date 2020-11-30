package com.syshology.blog.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoProfile {

public Integer id;
public String connected_at;
public Properties properties;
public KakaoAccount kakao_account;

}



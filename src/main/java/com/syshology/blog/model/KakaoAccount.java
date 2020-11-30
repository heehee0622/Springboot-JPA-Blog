package com.syshology.blog.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoAccount{
    public boolean profile_needs_agreement;
    public Profile profile;
    public boolean has_email;
    public boolean email_needs_agreement;
    public boolean is_email_valid;
    public boolean is_email_verified;
    public String email;
}


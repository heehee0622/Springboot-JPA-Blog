package com.syshology.blog.model;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@Builder
//@DynamicInsert insert시에 null인 필드를 인서트에서 빼준다.
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//DB의 연결된 넘버링 전략을 따라간다(시퀀스, 테이블, 오토, 아이덴티티)
	private int id;
	@Column(nullable = false, length = 30, unique = true)
	private String username;
	@Column(nullable = false, length = 100)
	private String password;
	@Column(nullable = false, length = 50)
	private String email;
	@CreationTimestamp
	private Timestamp createDate;
//	@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)
	private RoleType role;
	private String oauth;
}

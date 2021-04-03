package com.example.demo.client.api.entity;

import lombok.Data;

@Data
public class UserEntity {
	private Integer userId;
	private String username;
	private String password;
	private Integer isEnabled;
}

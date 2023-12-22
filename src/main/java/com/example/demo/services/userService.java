package com.example.demo.services;

import com.example.demo.entities.UserEntity;

public interface userService {
	
	public UserEntity creatUser(UserEntity user);
	public boolean checkEmail(String email);

}
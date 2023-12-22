package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserRepository;

@Service
public class UserServiceImp implements userService{
	
	
	@Autowired
	private UserRepository userRepository ;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	@Override
	public UserEntity creatUser(UserEntity user) {
		user.setPassword(passwordEncode.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		return userRepository.save(user);
	}

	@Override
	public boolean checkEmail(String email) {
		return userRepository.existsByEmail(email);
	}  

}

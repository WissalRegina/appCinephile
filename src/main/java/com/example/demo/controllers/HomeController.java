package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.UserEntity;
import com.example.demo.services.userService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private userService userS ;
	
	
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	
	@PostMapping("/createUser")
	public String createUser(@ModelAttribute UserEntity userEntity ,
			HttpSession session) {
		
		boolean f = userS.checkEmail(userEntity.getEmail());
		
		//email dèja existe 
		if (f) {
			session.setAttribute("msg", "Email Id alreday exists");
		}else {
			UserEntity user = userS.creatUser(userEntity);
				if(user != null) {
					session.setAttribute("msg", "Register Sucessfully");
				}else {
					session.setAttribute("msg", "Something wrong on server");
				}
		}
		
		return "redirect:/register";
	}
}

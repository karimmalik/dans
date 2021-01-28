package com.karim.dans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karim.dans.model.User;
import com.karim.dans.repository.UserRepository;

@RestController
@RequestMapping(path = "/list")
public class AppController {
	
	@Autowired
	UserRepository userRepository;
	

	
//	register
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "singup_form";
	}
	
	@PostMapping("process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encString = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encString);
		
		userRepository.save(user);
		return ("list");
	}
	
	

}

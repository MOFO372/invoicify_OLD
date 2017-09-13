package com.libertymutual.goforcode.invoicify.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.libertymutual.goforcode.invoicify.models.User;
import com.libertymutual.goforcode.invoicify.repositories.UserRepository;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private UserRepository userRepo; 
	private PasswordEncoder encoder;
	
	//THIS CONSTRUCTOR IS AN INJECTION EXAMPLE
	public HomeController(UserRepository userRepo, PasswordEncoder encoder) {
		this.userRepo = userRepo;
		this.encoder = encoder;
	}
	
	@GetMapping("/") 
	public String displayDefault() {
		return "home/default";
	}
	
	@GetMapping("/signup") 
	public ModelAndView signUp() {
		ModelAndView mv = new ModelAndView("home/signup"); 
		return mv; 
	}

	@PostMapping("/signup")
	public ModelAndView handleSignup(User user) {
		//THIS IS BAD, NEEDS REFACTORING
		String password = user.getPassword(); 
		String encryptedPassword = encoder.encode(password); 
		user.setPassword(encryptedPassword);
		
		ModelAndView mv = new ModelAndView(); 		
		try {
			userRepo.save(user);
			mv.setViewName("redirect:/login");
			
		} catch (DataIntegrityViolationException dive) {
			mv.setViewName("home/signup");
			mv.addObject("errorMessage", "No way, Jose. Pick a different username.");
		}
		
		return mv;
	}

}

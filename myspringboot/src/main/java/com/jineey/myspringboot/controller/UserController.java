package com.jineey.myspringboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jineey.myspringboot.entity.User;
import com.jineey.myspringboot.exception.CustomException;
import com.jineey.myspringboot.exception.ResourceNotFoundException;
import com.jineey.myspringboot.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex) {
		return new ModelAndView("error/generic_error", "errMsg", ex.getMessage());
	}
	
	@ExceptionHandler(CustomException.class)
	public ModelAndView handleCustomException(CustomException ex) {
		ModelAndView model = new ModelAndView("error/generic_error");
		model.addObject("errCode",ex.getErrCode());
		model.addObject("errMsg",ex.getErrMsg());
		return model;
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable long id) {
		//User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		User user = userRepository.findById(id).orElseThrow(() -> new CustomException("E001", String.format("요청하신 User Id %s 가 존재하지 않습니다!", id)));
		userRepository.delete(user);
		return "redirect:/index";
	}
	
	@PostMapping("/edituser/{id}")
	public String updateUser(@PathVariable long id, @Valid User user, BindingResult result) {
		System.out.println(">>> UpdateUser " + user);
		if(result.hasErrors()){
			//입력 필드값 체크했는데 입력 안했다.
			user.setId(id);
			return "update-user";
		}
		userRepository.save(user);
		return "redirect:/index";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView showUpdateForm(@PathVariable long id) {
		//Supplier : T get()
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return new ModelAndView("update-user","user",user);
	}
	
	@PostMapping("/adduser")
	public String addUser(@Valid User user, BindingResult result) {
		if(result.hasErrors()) {
			return "add-user";
		}
		userRepository.save(user);
		
		return "redirect:/index";
	}
	
	@GetMapping("/signup")
	public String showSignupForm(User user) {
		return "add-user";
	}
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("users",userRepository.findAll());
		return "index";
	}
	
	@GetMapping("/leaf")
	public ModelAndView leaf() {
		return new ModelAndView("leaf", "name", "웰컴 타임리프");
	}
}

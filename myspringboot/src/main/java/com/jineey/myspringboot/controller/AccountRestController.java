package com.jineey.myspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jineey.myspringboot.entity.Account;
import com.jineey.myspringboot.repository.AccountRepository;

@RestController
public class AccountRestController {
	@Autowired
	private AccountRepository repository;
	
	@GetMapping("/accounts")
	public List<Account> getAccounts(){
		return repository.findAll();
	}
}

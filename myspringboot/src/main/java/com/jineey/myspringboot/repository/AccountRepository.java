package com.jineey.myspringboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jineey.myspringboot.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	Optional<Account> findByUsername (String username);
}

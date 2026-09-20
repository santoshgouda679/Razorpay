package com.example.bank_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank_system.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer>{
	
}
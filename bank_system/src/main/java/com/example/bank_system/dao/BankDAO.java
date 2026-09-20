package com.example.bank_system.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.bank_system.entity.Bank;
import com.example.bank_system.repository.BankRepository;

@Repository
public class BankDAO {

	@Autowired
	BankRepository bankRepository;

	public String saveAcc(Bank bank) {
		bankRepository.save(bank);
		return "Data inserted";
	}

	public Object findById(int id) {
		Optional<Bank> op = bankRepository.findById(id);
		if (op.isPresent()) {
			return op.get();
		} else
			return "No data found with id" + id;
	}

	public List<Bank> findAll(Bank bank) {
		return bankRepository.findAll();
	}

	public Object updateAcc(Bank bank) {
		Optional<Bank> optional = bankRepository.findById(bank.getId());

		if (optional.isPresent()) {
			return bankRepository.save(bank);
		} else {
			return "No data found";
		}
	}
	
	public void deleteAll() {
		bankRepository.deleteAll();
	}
	
	public void deleteById(int id) {
		Optional<Bank> op = bankRepository.findById(id);
		
		if(op.isPresent()) {
			bankRepository.deleteById(id);
		}
	}
}
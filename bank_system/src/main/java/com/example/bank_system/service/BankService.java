package com.example.bank_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bank_system.entity.Bank;
import com.example.bank_system.exception.ResourceNotFoundException;
import com.example.bank_system.repository.BankRepository;

/**
 * Fixes from the original:
 *  1. Missing @Service - this class was never a Spring bean, so
 *     @Autowired BankDAO inside it could never actually be injected.
 *     (The controller side-stepped this by autowiring BankDAO directly,
 *     which is why nothing crashed - but that made this class dead code.)
 *  2. The separate BankDAO class just forwarded every call to
 *     BankRepository with no real logic of its own - removed, and its
 *     logic folded in here so there is one clear service layer between
 *     the controller and the repository.
 *  3. Methods now throw ResourceNotFoundException instead of returning a
 *     String/Object mix on failure, so callers (and the exception
 *     handler) can react consistently based on type instead of guessing
 *     from a String message.
 *  4. updateAcc(id, bank) takes the id from the URL path and merges the
 *     allowed fields onto the *existing* row, instead of blindly calling
 *     save(bank) with whatever id the request body happened to contain.
 */
@Service
public class BankService {

	@Autowired
	private BankRepository bankRepository;

	public Bank saveAcc(Bank bank) {
		return bankRepository.save(bank);
	}

	public Bank findById(int id) {
		return bankRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No account found with id " + id));
	}

	public List<Bank> findAll() {
		return bankRepository.findAll();
	}

	@Transactional
	public Bank updateAcc(int id, Bank bank) {
		Bank existing = bankRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No account found with id " + id));

		existing.setAccountHolderName(bank.getAccountHolderName());
		existing.setAccountNumber(bank.getAccountNumber());
		existing.setAccountType(bank.getAccountType());
		existing.setBalance(bank.getBalance());
		existing.setBranch(bank.getBranch());

		return bankRepository.save(existing);
	}

	public void deleteById(int id) {
		Bank existing = bankRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No account found with id " + id));
		bankRepository.delete(existing);
	}

	public void deleteAll() {
		bankRepository.deleteAll();
	}
}

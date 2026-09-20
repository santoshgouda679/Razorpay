package com.example.bank_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank_system.entity.Bank;
import com.example.bank_system.service.BankService;

import jakarta.validation.Valid;

/**
 * Fixes from the original:
 *  - GET /accounts/id and DELETE /accounts/id used @RequestBody Bank just
 *    to read the id. GET/DELETE requests conventionally don't carry a
 *    body, and most HTTP clients (and some proxies) drop it - so this
 *    would misbehave in practice. Both now take the id as a real
 *    @PathVariable ("/accounts/{id}").
 *  - findAll(Bank bank) accepted an unused Bank parameter bound from query
 *    params for no reason - removed.
 *  - Every method now returns ResponseEntity<> with an explicit status
 *    code (201 Created, 204 No Content, 404 via the exception handler,
 *    etc.) instead of returning a raw String/Object and always
 *    implicitly answering 200 OK even on failure.
 *  - @Valid added so a bad payload (blank name, negative balance, ...) is
 *    rejected with a 400 and field-level messages before it ever reaches
 *    the database.
 *  - Every endpoint here now requires a valid JWT (see SecurityConfig) -
 *    previously anyone could create/update/wipe accounts with no
 *    authentication at all.
 */
@RestController
@RequestMapping("/accounts")
public class BankController {

	@Autowired
	private BankService bankService;

	@PostMapping
	public ResponseEntity<Bank> saveAcc(@Valid @RequestBody Bank bank) {
		Bank saved = bankService.saveAcc(bank);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Bank> findById(@PathVariable int id) {
		return ResponseEntity.ok(bankService.findById(id));
	}

	@GetMapping
	public ResponseEntity<List<Bank>> findAll() {
		return ResponseEntity.ok(bankService.findAll());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Bank> updateAcc(@PathVariable int id, @Valid @RequestBody Bank bank) {
		return ResponseEntity.ok(bankService.updateAcc(id, bank));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable int id) {
		bankService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping
	public ResponseEntity<String> deleteAll() {
		bankService.deleteAll();
		return ResponseEntity.ok("Data deleted successfully");
	}
}

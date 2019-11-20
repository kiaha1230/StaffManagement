package com.team3.dumb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team3.customModel.AccountCustom;
import com.team3.model.Account;
import com.team3.model.Depart;
import com.team3.service.AccountService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/salgrade")
public class SalgradeController {
	@Autowired
	private SalgradeService salgradeService;

//	@GetMapping("accounts")
//	public ArrayList<Account> getAllAccount() {
//		return salgradeService.getAllAccount();
//	}

//	@GetMapping("/accounts/{id}")
//	public Optional<Account> getById(@PathVariable int id) {
//		return salgradeService.getById(id);
//	}

	@PostMapping("/add")
	public void addDepart(@RequestBody Account account) {
		salgradeService.addAccount(account);
	}

	@PutMapping("/edit")
	public void editDepart(@RequestBody Account account) {
		salgradeService.editAccount(account);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteAccount(@PathVariable Integer id) {
		salgradeService.deleteAccount(id);
	}

	@PostMapping("/getsByConditions")
	public ArrayList<Salgrade> getByCondition(@RequestBody Salgrade salgrade) {
		return salgradeService.getByCondition(salgrade);
	}

	

}

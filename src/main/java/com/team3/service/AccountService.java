package com.team3.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.customModel.AccountCustom;
import com.team3.model.Account;
import com.team3.model.Depart;
import com.team3.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	@PersistenceContext
	private EntityManager em;

	public ArrayList<Account> getAllAccount() {
		List<Account> list = new ArrayList<Account>();
		list = accountRepository.findAll();
		return (ArrayList<Account>) list;
	}

	public Optional<Account> getById(int id) {
		return accountRepository.findById(id);
	}

	public void addAccount(Account account) {
		accountRepository.save(account);
	}

	public void editAccount(Account account) {
		accountRepository.save(account);
	}

	public void deleteAccount(int id) {
		accountRepository.deleteById(id);
	}

	public ArrayList<AccountCustom> getByCondition(Account account) {
		ArrayList<AccountCustom> list = new ArrayList<AccountCustom>();
		String query = "select a.id, a.username,a.password,a.createDate, s.staffName,a.accountRole from Account a , Staff s where a.staffId = s.id ";
		if (!(account.getUsername() == null)) {
			query += " and  a.username like :username";
		}
		if (!(account.getStaffId() == null)) {
			query += " and s.id = :staffId  ";
		}
		if (account.isAccountRole() != null) {
			query += " and a.account_role = :accountRole ";
		}
		Query q = em.createQuery(query);
		if (account.getUsername() != null) {
			q.setParameter("username", account.getUsername());
		}
		if (!(account.getStaffId() == null)) {
			q.setParameter("staffId", account.getStaffId());
		}
		if (account.isAccountRole() != null) {
			q.setParameter("accountRole", account.isAccountRole());
		}

		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((record) -> {
			AccountCustom custom = new AccountCustom();
			custom.setId((Integer) record[0]);
			custom.setUsername(record[1].toString());
			custom.setPassword(record[2].toString());
			custom.setCreateDate((Date) record[3]);
			custom.setStaffName(record[4].toString());
			custom.setAccountRole((Boolean) record[5]);
			list.add(custom);
		});
		return list;
	}

}

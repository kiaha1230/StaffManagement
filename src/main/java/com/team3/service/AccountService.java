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

	public ArrayList<Account> getByCondition(Account account) {
		ArrayList<Account> list = new ArrayList<Account>();
		String query = "select a.id, a.username,a.password,a.createDate, s.staffName,a.accountRole from Account a , Staff s where a.staffId = s.id ";
		Date fromDate = new Date();
		Date toDate = new Date();
		Date createDate = new Date();
		if (!(account.getUsername() == null)) {
			query += " and  a.username like :username";
		}
		// ngay
		if (account.getFromDate() != null && account.getToDate() != null) {
			query += " and a.createDate between :fromDate and :toDate ";
		}
		if (account.getFromDate() != null && account.getToDate() == null) {
			query += " and a.createDate >= :fromDate ";
		}
		if (account.getFromDate() == null && account.getToDate() != null) {
			query += " and a.createDate <= :toDate ";
		}

		//
		if (!(account.getStaffId() == null)) {
			query += " and s.id = :staffId  ";
		}
		if (account.isAccountRole() != null) {
			query += " and a.accountRole = :accountRole ";
		}
		Query q = em.createQuery(query);
		// set parameter
		if (account.getUsername() != null) {
			q.setParameter("username", "%" + account.getUsername() + "%");
		}
		if (!(account.getStaffId() == null)) {
			q.setParameter("staffId", account.getStaffId());
		}
		if (account.isAccountRole() != null) {
			q.setParameter("accountRole", account.isAccountRole());
		}
		if (account.getFromDate() != null && account.getToDate() != null) {
			q.setParameter("fromDate", account.getFromDate());
			q.setParameter("toDate", account.getToDate());
		}
		if (account.getFromDate() != null && account.getToDate() == null) {
			q.setParameter("createDate", account.getCreateDate());
			q.setParameter("fromDate", account.getFromDate());
		}
		if (account.getFromDate() == null && account.getToDate() != null) {
			q.setParameter("createDate", account.getCreateDate());
			q.setParameter("toDate", account.getToDate());
		}

		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((record) -> {
			Account custom = new Account();
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

	public List<Account> login(Account account) {
		List<Account> list = new ArrayList<Account>();
		String hql = "FROM Account WHERE username = :username and password = :password ";
		Query q = em.createQuery(hql);
		q.setParameter("username", account.getUsername());
		q.setParameter("password", account.getPassword());
		list = q.getResultList();
		return list;
	}

}

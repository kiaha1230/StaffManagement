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


import com.team3.model.Account;
import com.team3.model.Salgrade;

import com.team3.repository.SalgradeRepository;

@Service
public class SalgradeService {
	@Autowired
	private SalgradeRepository salgradeRepository;
	@PersistenceContext
	private EntityManager em;

	public ArrayList<Account> getAllAccount() {
		List<Account> list = new ArrayList<Account>();
		list = salgradeRepository.findAll();
		return (ArrayList<Account>) list;
	}

	public Optional<Account> getById(int id) {
		return salgradeRepository.findById(id);
	}

	public void addAccount(Account account) {
		salgradeRepository.save(account);
	}

	public void editAccount(Account account) {
		salgradeRepository.save(account);
	}

	public void deleteAccount(int id) {
		salgradeRepository.deleteById(id);
	}

	public ArrayList<Salgrade> getByCondition(Salgrade salgrade) {
		ArrayList<Salgrade> list = new ArrayList<Salgrade>();
		String sql = "FROM Salgrade ";
		Query q = em.createQuery(sql);
		list = (ArrayList<Salgrade>) q.getResultList();
		return list;
	}

}

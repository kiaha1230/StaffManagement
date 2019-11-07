package com.team3.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.model.Account;
import com.team3.repository.AccountRepository;

@Service
public class UltilitiesService {
	@Autowired
	AccountRepository accountRepository;
	@PersistenceContext
	private EntityManager em;

	

}

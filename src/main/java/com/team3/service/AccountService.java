package com.team3.service;

import java.awt.AlphaComposite;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.Ultilities.Ultilities;
import com.team3.bean.Mailer;
import com.team3.customModel.AccountCustom;

import com.team3.model.APIResponse;
import com.team3.model.Account;
import com.team3.model.Depart;
import com.team3.model.Pager;
import com.team3.model.Record;
import com.team3.model.Staff;
import com.team3.repository.AccountRepository;
import com.team3.repository.StaffRepository;
import com.team3.resources.UserInformation;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private StaffRepository staffRepository;
	@PersistenceContext
	private EntityManager em;
	@Autowired
	Mailer mailer;

	public ArrayList<Account> getAllAccount() {
		List<Account> list = new ArrayList<Account>();
		list = accountRepository.findAll();
		return (ArrayList<Account>) list;
	}

	public Optional<Account> getById(int id) {
		return accountRepository.findById(id);
	}

	public void addAccount(Account account) {
		Optional<Staff> staff = staffRepository.findById(account.getStaffId());
		String password = randomPassword();
		account.setPassword(password);
		String from = "VIS-HR";
		String subject = "Reset mật khẩu VISSOFT";
		String body = "Mật khẩu mới của bạn là: " + password + ". Vui lòng đổi mật khẩu mới khi đăng nhập";
		mailer.send(from, staff.get().getEmail(), subject, body);
		accountRepository.save(account);

	}

	public void editAccount(Account account) {
		accountRepository.save(account);

	}

	public void deleteAccount(int id) {
		accountRepository.deleteById(id);
	}

	public Account getByIdSQL(Integer id) {
		Account account = new Account();
		String query = "from Account where id = :id ";
		Query q = em.createQuery(query);
		q.setParameter("id", id);
		account = (Account) q.getSingleResult();
		return account;

	}

	public APIResponse getByConditionPager(Account account) {
		ArrayList<Account> list = new ArrayList<Account>();
		String query = "select  a.id, a.username,a.password,a.createDate, s.staffName,a.accountRole,a.staffId,s.staffCode from Account a , Staff s where a.staffId = s.id ";
		Date fromDate = new Date();
		Date toDate = new Date();
		Date createDate = new Date();
		if (!(account.getUsername() == null)) {
			query += " and  a.username like :username";
		}
		// ngay
		if (account.getFromCreateDate() != null && account.getToCreateDate() != null) {
			query += " and a.createDate between :fromDate and :toDate ";
		}
		if (account.getFromCreateDate() != null && account.getToCreateDate() == null) {
			query += " and a.createDate >= :fromDate ";
		}
		if (account.getFromCreateDate() == null && account.getToCreateDate() != null) {
			query += " and a.createDate <= :toDate ";
		}

		//
		if (!(account.getStaffId() == null)) {
			query += " and s.id = :staffId  ";
		}
		if (account.getAccountRole() != null) {
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
		if (account.getAccountRole() != null) {
			q.setParameter("accountRole", account.getAccountRole());
		}
		if (account.getFromCreateDate() != null && account.getToCreateDate() != null) {
			q.setParameter("fromDate", account.getFromCreateDate());
			q.setParameter("toDate", account.getToCreateDate());
		}
		if (account.getFromCreateDate() != null && account.getToCreateDate() == null) {
			q.setParameter("fromDate", account.getFromCreateDate());
		}
		if (account.getFromCreateDate() == null && account.getToCreateDate() != null) {
			q.setParameter("toDate", account.getToCreateDate());
		}

		APIResponse response = new APIResponse();
		Pager pager = new Pager();
		List<Object[]> totalRow = q.getResultList();
		pager.setTotalRow(totalRow.size());
		q.setFirstResult(account.getPager().getPage() * account.getPager().getPageSize());
		q.setMaxResults(account.getPager().getPageSize());

		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((record) -> {
			Account custom = new Account();
			custom.setId((Integer) record[0]);
			custom.setUsername(record[1].toString());
			custom.setPassword(record[2].toString());
			custom.setCreateDate((Date) record[3]);
			custom.setStaffName(record[4].toString());
			custom.setAccountRole((Integer) record[5]);
			custom.setStaffId((Integer) record[6]);
			custom.setStaffCode((String) record[7]);
			list.add(custom);
		});

		pager.setPageSize(account.getPager().getPageSize());
		pager.setPage(account.getPager().getPage());
		response.setPager(pager);
		response.setData(list);
		return response;
	}

	public Account login(Account account) {
		if (account.getUsername() == null || account.getPassword() == null) {
			return null;
		}
		Account account1 = new Account();
		String hql = "FROM Account WHERE username = :username and password = :password ";
		Query q = em.createQuery(hql);
		q.setParameter("username", account.getUsername());
		q.setParameter("password", account.getPassword());
		account1 = (Account) q.getResultList().stream().findFirst().orElse(null);
		if (account1 != null) {
			if (account.getPassword().equals(account1.getPassword())) {
				UserInformation.setACCOUNT(account1);
				return account1;
			} else {
				return null;
			}
		}

		return account1;
	}

	public void logout() {
		UserInformation.setACCOUNT(null);
	}

	public List<Account> getAccountByStaff() {
		List<Account> list = new ArrayList<Account>();
		String hql = "select a.id,s.staffName,s.staffCode  from Staff s, Account a where s.id = a.staffId ";
		Query q = em.createQuery(hql);
		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((record) -> {
			Account custom = new Account();
			custom.setId((Integer) record[0]);
			custom.setStaffName(record[1].toString());
			custom.setStaffCode(record[2].toString());
			list.add(custom);
		});
		return list;
	}

	public List<String> getlistUsername() {
		List<String> list = new ArrayList<String>();
		List<String> account = new ArrayList<String>();
		String hql = " select username from Account";
		Query q = em.createQuery(hql);
		list = q.getResultList();
		return list;

	}

	public Boolean checkMatchUsernameEmail(String email, String username) {
		Account accounts = new Account();
		String hql = "from Account where id in (select a.id from Account a , Staff s  where a.staffId = s.id and s.email = :email and a.username = :username) ";
		Query q = em.createQuery(hql);
		q.setParameter("email", email);
		q.setParameter("username", username);
		accounts = (Account) q.getResultList().stream().findFirst().orElse(null);
		if (accounts != null) {
			String password = randomPassword();
			sendMailResetPassword(email, password);
			accounts.setPassword(password);
			accountRepository.save(accounts);
			return true;
		} else {
			return false;
		}
	}

	public String randomPassword() {
		String password = "";
		final int length = 8;
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < length; i++) {
			password += chars.charAt((int) Math.floor(Math.random() * chars.length()));
		}
		return password;
	}

	public void sendMailResetPassword(String email, String password) {
		String from = "VIS-HR";
		String subject = "Reset mật khẩu VISSOFT";
		String body = "Mật khẩu mới của bạn là: " + password + ". Vui lòng đổi mật khẩu mới khi đăng nhập";
		mailer.send(from, email, subject, body);
	}

}

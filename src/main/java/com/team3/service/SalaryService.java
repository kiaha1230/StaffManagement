package com.team3.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.xml.messaging.saaj.packaging.mime.util.QEncoderStream;
import com.team3.customModel.SalaryCustom;
import com.team3.model.Account;
import com.team3.model.Record;
import com.team3.model.Salary;
import com.team3.repository.SalaryRepository;

@Service
public class SalaryService {
	@Autowired
	private SalaryRepository salaryRepository;
	@PersistenceContext
	private EntityManager em;

	public ArrayList<Salary> getAllSalary() {
		return (ArrayList<Salary>) salaryRepository.findAll();
	}

	public Optional<Salary> getById(int id) {
		return salaryRepository.findById(id);
	}

	public void addOrEditSalary(Salary salary) {
		salaryRepository.save(salary);
	}

	public void deleteSalary(int id) {
		salaryRepository.deleteById(id);
	}

	public ArrayList<Salary> findByCondition(Salary salary) {
		ArrayList<Salary> list = new ArrayList<Salary>();
		String query = "select sa.id,s.staffName,sa.grossSalary,sa.tax, sa.insurance,sa.netSalary from Salary sa , Staff s where sa.staffId = s.id ";
		if (!(salary.getStaffId() == null)) {
			query += " and  sa.staffId = :staffId";
		}
		if (salary.getFromGrossSalary() != null && salary.getToGrossSalary() != null) {
			query += " and sa.grossSalary between :fromGross and :toGross ";
		}
		if (salary.getFromGrossSalary() != null && salary.getToGrossSalary() == null) {
			query += " and sa.grossSalary >= :fromGross ";
		}
		if (salary.getFromGrossSalary() == null && salary.getToGrossSalary() != null) {
			query += " and sa.grossSalary <= :toGross ";
		}
		////////

		if (salary.getFromNetSalary() != null && salary.getToNetSalary() != null) {
			query += " and sa.netSalary between :fromNet and :toNet ";
		}
		if (salary.getFromNetSalary() != null && salary.getToNetSalary() == null) {
			query += " and sa.netSalary >= :fromNet ";
		}
		if (salary.getFromNetSalary() == null && salary.getToNetSalary() != null) {
			query += " and sa.netSalary <= :toNet ";
		}
//		if (!(salary.getSalGrade() == null)) {
//			query += "	and grossSalary between losal and hisal and grade = :grade ";
//		}
		Query q = em.createQuery(query);
		if (!(salary.getStaffId() == null)) {
			q.setParameter("staffId", salary.getStaffId());
		}
		if (salary.getFromGrossSalary() != null && salary.getToGrossSalary() != null) {
			q.setParameter("fromGross", salary.getFromGrossSalary());
			q.setParameter("toGross", salary.getToGrossSalary());
		}
		if (salary.getFromGrossSalary() != null && salary.getToGrossSalary() == null) {
			q.setParameter("fromGross", salary.getFromGrossSalary());
		}
		if (salary.getFromGrossSalary() == null && salary.getToGrossSalary() != null) {
			q.setParameter("toGross", salary.getToGrossSalary());
		}

		/////

		if (salary.getFromNetSalary() != null && salary.getToNetSalary() != null) {
			q.setParameter("fromNet", salary.getFromNetSalary());
			q.setParameter("toNet", salary.getToNetSalary());
		}
		if (salary.getFromNetSalary() != null && salary.getToNetSalary() == null) {
			q.setParameter("fromNet", salary.getFromNetSalary());
		}
		if (salary.getFromNetSalary() == null && salary.getToNetSalary() != null) {
			q.setParameter("toNet", salary.getToNetSalary());
		}
//		if (!(salary.getSalGrade() == null)) {
//			q.setParameter("toNet", salary.getToNetSalary());
//		}
		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((records) -> {
			Salary custom = new Salary();
			custom.setId((Integer) records[0]);
			custom.setStaffName((String) records[1]);
			custom.setGrossSalary((Double) records[2]);
			custom.setTax((Double) records[3]);
			custom.setInsurance((Double) records[4]);
			custom.setNetSalary((Double) records[5]);
			list.add(custom);
		});
		return list;
	}

}

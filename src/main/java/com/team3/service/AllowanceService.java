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

import com.team3.model.APIResponse;
import com.team3.model.Allowance;
import com.team3.model.Pager;
import com.team3.repository.AllowanceRepository;

@Service
public class AllowanceService {
	@Autowired
	private AllowanceRepository allowanceRepository;
	@PersistenceContext
	private EntityManager em;

	public ArrayList<Allowance> getAllAllowance() {
		List<Allowance> list = new ArrayList<Allowance>();
		list = allowanceRepository.findAll();
		return (ArrayList<Allowance>) list;
	}

	public Optional<Allowance> getById(int id) {
		return allowanceRepository.findById(id);
	}

	public void addAllowance(Allowance allowance) {
		allowanceRepository.save(allowance);
	}

	public void editAllowance(Allowance allowance) {
		allowanceRepository.save(allowance);
	}

	public void deleteAllowance(int id) {
		allowanceRepository.deleteById(id);
	}

	// API
	public APIResponse getByCondition(Allowance allowance) {
		ArrayList<Allowance> list = new ArrayList<Allowance>();
		String query = "select a.id, s.staffName,a.travelAllowance,a.deviceAllowance, a.mealAllowance,a.staffId,s.staffCode from Allowance a , Staff s where a.staffId = s.id ";
		if ((allowance.getStaffId() != null)) {
			query += " and  a.staffId = :staffId";
		}
		// from to travel
		if (allowance.getFromTravel() != null && allowance.getToTravel() != null) {
			query += " and a.travelAllowance between :fromTravel and :toTravel ";
		}
		if (allowance.getFromTravel() != null && allowance.getToTravel() == null) {
			query += " and a.travelAllowance >= :fromTravel ";
		}
		if (allowance.getFromTravel() == null && allowance.getToTravel() != null) {
			query += " and a.travelAllowance <= :toTravel ";
		}

		// from to device
		if (allowance.getFromDevice() != null && allowance.getToDevice() != null) {
			query += " and a.deviceAllowance between :fromDevice and :toDevice ";
		}
		if (allowance.getFromDevice() != null && allowance.getToDevice() == null) {
			query += " and a.deviceAllowance >= :fromDevice ";
		}
		if (allowance.getFromDevice() == null && allowance.getToDevice() != null) {
			query += " and a.deviceAllowance <= :toDevice ";
		}
		// from to meal

		if (allowance.getFromMeal() != null && allowance.getToMeal() != null) {
			query += " and a.mealAllowance between :fromMeal and :toMeal ";
		}
		if (allowance.getFromMeal() != null && allowance.getToMeal() == null) {
			query += " and a.mealAllowance >= :fromMeal ";
		}
		if (allowance.getFromMeal() == null && allowance.getToMeal() != null) {
			query += " and a.mealAllowance <= :toMeal ";
		}
		//

		Query q = em.createQuery(query);
		// set parameter
		if ((allowance.getStaffId() != null)) {
			q.setParameter("staffId", allowance.getStaffId());
		}
		if (allowance.getFromTravel() != null && allowance.getToTravel() != null) {
			q.setParameter("fromTravel", allowance.getFromTravel());
			q.setParameter("toTravel", allowance.getToTravel());
		}
		if (allowance.getFromTravel() != null && allowance.getToTravel() == null) {
			q.setParameter("fromTravel", allowance.getFromTravel());
		}
		if (allowance.getFromTravel() == null && allowance.getToTravel() != null) {
			q.setParameter("toTravel", allowance.getToTravel());
		}
		//

		if (allowance.getFromDevice() != null && allowance.getToDevice() != null) {
			q.setParameter("fromDevice", allowance.getFromDevice());
			q.setParameter("toDevice", allowance.getToDevice());
		}
		if (allowance.getFromDevice() != null && allowance.getToDevice() == null) {
			q.setParameter("fromDevice", allowance.getFromDevice());
		}
		if (allowance.getFromDevice() == null && allowance.getToDevice() != null) {
			q.setParameter("toDevice", allowance.getToDevice());
		}
		//

		if (allowance.getFromMeal() != null && allowance.getToMeal() != null) {
			q.setParameter("fromMeal", allowance.getFromMeal());
			q.setParameter("toMeal", allowance.getToMeal());
		}
		if (allowance.getFromMeal() != null && allowance.getToMeal() == null) {
			q.setParameter("fromMeal", allowance.getFromMeal());
		}
		if (allowance.getFromMeal() == null && allowance.getToMeal() != null) {
			q.setParameter("toMeal", allowance.getToMeal());
		}

		APIResponse response = new APIResponse();
		Pager pager = new Pager();
		List<Object[]> totalRow = q.getResultList();
		pager.setTotalRow(totalRow.size());
		q.setFirstResult(allowance.getPager().getPage() * allowance.getPager().getPageSize());
		q.setMaxResults(allowance.getPager().getPageSize());

		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((record) -> {
			Allowance custom = new Allowance();
			custom.setId((Integer) record[0]);
			custom.setStaffName((String) record[1]);
			custom.setTravelAllowance((Double) record[2]);
			custom.setDeviceAllowance((Double) record[3]);
			custom.setMealAllowance((Double) record[4]);
			custom.setStaffId((Integer) record[5]);
			custom.setStaffCode((String) record[6]);
			list.add(custom);
		});

		pager.setPageSize(allowance.getPager().getPageSize());
		pager.setPage(allowance.getPager().getPage());
		response.setPager(pager);
		response.setData(list);
		return response;

	}

	public Allowance getByIdSQL(Integer id) {
		Allowance allowance = new Allowance();
		String hql = "From Allowance where staffId = :id";
		Query q = em.createQuery(hql);
		q.setParameter("id", id);
		allowance = (Allowance) q.getResultList().stream().findFirst().orElse(null);
		return allowance;
	}

}

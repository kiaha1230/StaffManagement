package com.team3.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.customModel.AccountCustom;
import com.team3.model.APIResponse;
import com.team3.model.Depart;
import com.team3.model.Pager;
import com.team3.repository.DepartRepository;

@Service
public class DepartService {
	@Autowired
	private DepartRepository departRepository;
	@PersistenceContext
	private EntityManager em;

	public ArrayList<Depart> getAllDepart() {
		List<Depart> list = new ArrayList<Depart>();
		list = departRepository.findAll();
		return (ArrayList<Depart>) list;
	}

	public Optional<Depart> getById(int id) {
		return departRepository.findById(id);
	}

	public void addDepart(Depart depart) {
		departRepository.save(depart);
	}

	public void editDepart(Depart depart) {
		departRepository.save(depart);
	}

	public void deleteDepart(int id) {
		departRepository.deleteById(id);
	}

//	public ArrayList<Depart> findByCondition(Depart depart) {
//		ArrayList<Depart> list = new ArrayList<Depart>();
//		String query = "from Depart where id >0 ";
//		if (!(depart.getDepartCode() == null)) {
//			query += " and  departCode like :departCode";
//		}
//		if (!(depart.getDepartName() == null)) {
//			query += " and departName like :departName  ";
//		}
//		Query q = em.createQuery(query);
//		if (!(depart.getDepartCode() == null)) {
//			q.setParameter("departCode", "%" + depart.getDepartCode() + "%");
//		}
//		if (!(depart.getDepartName() == null)) {
//			q.setParameter("departName", "%" + depart.getDepartName() + "%");
//		}
//		
//
//		list = (ArrayList<Depart>) q.getResultList();
//		return list;
//	}

	// API

	public APIResponse findByCondition(Depart depart) {
		ArrayList<Depart> list = new ArrayList<Depart>();
		String query = "from Depart where id >0 ";
		if (!(depart.getDepartCode() == null)) {
			query += " and  departCode like :departCode";
		}
		if (!(depart.getDepartName() == null)) {
			query += " and departName like :departName  ";
		}
		Query q = em.createQuery(query);
		if (!(depart.getDepartCode() == null)) {
			q.setParameter("departCode", "%" + depart.getDepartCode() + "%");
		}
		if (!(depart.getDepartName() == null)) {
			q.setParameter("departName", "%" + depart.getDepartName() + "%");
		}

		APIResponse response = new APIResponse();
		Pager pager = new Pager();
		List<Object[]> totalRow = q.getResultList();
		pager.setTotalRow(totalRow.size());
		q.setFirstResult(depart.getPager().getPage() * depart.getPager().getPageSize());
		q.setMaxResults(depart.getPager().getPageSize());

		list = (ArrayList<Depart>) q.getResultList();
		
		
		pager.setPageSize(depart.getPager().getPageSize());
		pager.setPage(depart.getPager().getPage());
		response.setPager(pager);
		response.setData(list);
		return response;
	}

}

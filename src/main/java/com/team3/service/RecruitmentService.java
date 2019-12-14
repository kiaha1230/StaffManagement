package com.team3.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.team3.model.APIResponse;
import com.team3.model.Depart;
import com.team3.model.Pager;
import com.team3.model.Recruitment;
import com.team3.repository.RecruitmentRepository;

@Service
public class RecruitmentService {
	@Autowired
	private RecruitmentRepository recruitmentRepository;
	@PersistenceContext
	private EntityManager em;

	public ArrayList<Recruitment> getAllRecruitment() {
		return (ArrayList<Recruitment>) recruitmentRepository.findAll();
	}

	public Optional<Recruitment> getById(Integer id) {
		return recruitmentRepository.findById(id);
	}

	public void addOrEditRecruitment(Recruitment recruitment) {
		recruitmentRepository.save(recruitment);

	}

	public void deleteRecruitment(Integer id) {
		recruitmentRepository.deleteById(id);
	}

	// API

	public APIResponse findByCondition(Recruitment recruitment) {

		// code like , title like
		ArrayList<Recruitment> list = new ArrayList<Recruitment>();
		String query = "select r.id,r.recruitmentCode,r.title,r.status, r.description,r.staffId,s.staffName,s.staffCode from Recruitment r , Staff s  where r.staffId = s.id ";
		if (!(recruitment.getRecruitmentCode() == null)) {
			query += " and  r.recruitmentCode like :recruitmentCode";
		}
		if (!(recruitment.getStatus() == null)) {
			query += " and  r.status = :status";
		}
		if (!(recruitment.getTitle() == null)) {
			query += " and  r.title like :title";
		}
		Query q = em.createQuery(query);
		if (!(recruitment.getRecruitmentCode() == null)) {
			q.setParameter("recruitmentCode", "%" + recruitment.getRecruitmentCode() + "%");
		}
		if (!(recruitment.getTitle() == null)) {
			q.setParameter("title", "%" + recruitment.getTitle() + "%");
		}
		if (!(recruitment.getStatus() == null)) {
			q.setParameter("status", recruitment.getStatus());
		}

		APIResponse response = new APIResponse();
		Pager pager = new Pager();
		List<Object[]> totalRow = q.getResultList();
		pager.setTotalRow(totalRow.size());
		q.setFirstResult(recruitment.getPager().getPage() * recruitment.getPager().getPageSize());
		q.setMaxResults(recruitment.getPager().getPageSize());

		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((recruitments) -> {
			Recruitment custom = new Recruitment();
			custom.setId((Integer) recruitments[0]);
			custom.setRecruitmentCode((String) recruitments[1]);
			custom.setTitle((String) recruitments[2]);
			custom.setStatus((Boolean) recruitments[3]);
			custom.setDescription(recruitments[4].toString());
			custom.setStaffId((Integer) recruitments[5]);
			custom.setStaffName((String) recruitments[6]);
			custom.setStaffCode((String) recruitments[7]);

			list.add(custom);
		});

		pager.setPageSize(recruitment.getPager().getPageSize());
		pager.setPage(recruitment.getPager().getPage());
		response.setPager(pager);
		response.setData(list);
		return response;
	}

	public Boolean checkRecruitmentCodeDuplicate(String recruitmentCode) {
		Recruitment recruitment = new Recruitment();
		String hql = " from Recruitment where recruitmentCode = :recruitmentCode  ";
		Query q = em.createQuery(hql);
		q.setParameter("recruitmentCode", recruitmentCode);
		recruitment = (Recruitment) q.getResultList().stream().findFirst().orElse(null);
		if (recruitment == null) {
			return true;
		} else {
			return false;
		}
	}

	public Recruitment getByIdSQL(Integer id) {
		Recruitment recruitment = new Recruitment();
		String hql = "From Recruitment where id = :id";
		Query q = em.createQuery(hql);
		q.setParameter("id", id);
		recruitment = (Recruitment) q.getSingleResult();
		return recruitment;
	}

	public List<String> getListRecruitmentCode() {
		List<String> list = new ArrayList<String>();
		String hql = " select recruitmentCode from Recruitment";
		Query q = em.createQuery(hql);
		list = q.getResultList();
		return list;

	}

}

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

	public ArrayList<Recruitment> findByCondition(Recruitment recruitment) {

		// code like , title like
		ArrayList<Recruitment> list = new ArrayList<Recruitment>();
		String query = "select r.id, s.staffName,r.recruitmentCode,r.title, s.description from Recruitment r , Staff s where r.staffId = s.id ";
		if (!(recruitment.getRecruitmentCode() == null)) {
			query += " and  r.recruitmentCode like :recruitmentCode";
		}
		if (!(recruitment.getTitle() == null)) {
			query += " and  r.title like :title";
		}
		Query q = em.createQuery(query);
		if (!(recruitment.getRecruitmentCode() == null)) {
			q.setParameter("recruitmentCode", recruitment.getRecruitmentCode());
		}
		if (!(recruitment.getTitle() == null)) {
			query += " and  r.title like :title";
		}
		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((recruitments) -> {
			Recruitment custom = new Recruitment();
			custom.setId((Integer) recruitments[0]);
			custom.setStaffName((String) recruitments[1]);
			custom.setRecruitmentCode((String) recruitments[2]);
			custom.setTitle((String) recruitments[3]);
			custom.setDescription(recruitments[4].toString());
			list.add(custom);
		});
		return list;
	}

}

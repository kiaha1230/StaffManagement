package com.team3.dumb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.model.Account;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	public ArrayList<Project> getAllProject() {
		List<Project> list = new ArrayList<Project>();
		list = projectRepository.findAll();
		return (ArrayList<Project>) list;
	}

	public Optional<Project> getById(Integer id) {
		return projectRepository.findById(id);
	}

	public void addOrEditProject(Project project) {
		projectRepository.save(project);

	}

	public void deleteProject(Integer id) {
		projectRepository.deleteById(id);
	}

//	public ArrayList<Account> getByCondition(String projectCode, String projectName, Integer departId, Boolean status) {
//		ArrayList<Account> list = new ArrayList<Account>();
//		String query = " SELECT a  FROM Account a where p.status >0 ";
//		if (!projectCode.equals("")) {
//			query += " and  a.projectCode = :projectCode  ";
//		}
//		if ((staffId != null)) {
//			query += " and a.staffId = :staffId ";
//
//		}
//		if (accountRole != null) {
//			query += " and a.accountRole = :accountRole ";
//
//		}
//		Query q = em.createQuery(query);
//		if (!username.equals("")) {
//			q.setParameter("username", username);
//		}
//		if ((staffId != null)) {
//			q.setParameter("staffId", staffId);
//		}
//		if (accountRole != null) {
//			q.setParameter("accountRole", accountRole);
//
//		}
//		list = (ArrayList<Account>) q.getResultList();
//		return list;
//	}

}

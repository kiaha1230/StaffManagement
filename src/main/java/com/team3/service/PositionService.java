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
import com.team3.model.Account;
import com.team3.model.Position;
import com.team3.model.Pager;
import com.team3.model.Position;
import com.team3.repository.PositionRepository;
import com.team3.resources.UserInformation;
import com.team3.repository.PositionRepository;

@Service
public class PositionService {
	@Autowired
	private PositionRepository positionRepository;
	@PersistenceContext
	private EntityManager em;

	public ArrayList<Position> getAllPosition() {
		List<Position> list = new ArrayList<Position>();
		list = positionRepository.findAll();
		return (ArrayList<Position>) list;
	}

	public Optional<Position> getById(int id) {
		return positionRepository.findById(id);
	}

	public void addPosition(Position position) {
		positionRepository.save(position);
	}

	public void editPosition(Position position) {
		positionRepository.save(position);
	}

	public void deletePosition(int id) {
		positionRepository.deleteById(id);
	}

	public APIResponse findByCondition(Position position) {
		ArrayList<Position> list = new ArrayList<Position>();
		String query = "from Position where id >0 ";
		if (!(position.getDescription() == null)) {
			query += " and  description like :description";
		}
		if (!(position.getPositionName() == null)) {
			query += " and positionName like :positionName  ";
		}
		Query q = em.createQuery(query);
		if (!(position.getDescription() == null)) {
			q.setParameter("description", "%" + position.getDescription() + "%");
		}
		if (!(position.getPositionName() == null)) {
			q.setParameter("positionName", "%" + position.getPositionName() + "%");
		}

		APIResponse response = new APIResponse();
		Pager pager = new Pager();
		List<Object[]> totalRow = q.getResultList();
		pager.setTotalRow(totalRow.size());
		q.setFirstResult(position.getPager().getPage() * position.getPager().getPageSize());
		q.setMaxResults(position.getPager().getPageSize());

		list = (ArrayList<Position>) q.getResultList();

		pager.setPageSize(position.getPager().getPageSize());
		pager.setPage(position.getPager().getPage());
		response.setPager(pager);
		response.setData(list);
		return response;
	}

	public Position getPositionById(Integer id) {
		Position position = new Position();
		String hql = "FROM Position WHERE id = :id ";
		Query q = em.createQuery(hql);
		q.setParameter("id", id);
		position = (Position) q.getResultList().stream().findFirst().orElse(null);
		return position;
	}

}

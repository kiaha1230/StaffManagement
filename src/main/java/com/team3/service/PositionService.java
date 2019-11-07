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

import com.team3.model.Position;
import com.team3.repository.PositionRepository;
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

	public ArrayList<Position> getByCondition(Position position) {
		ArrayList<Position> list = new ArrayList<Position>();
		String query = "FROM Position";
		Query q = em.createQuery(query);
		list = (ArrayList<Position>) q.getResultList();
		return list;
	}

	

}

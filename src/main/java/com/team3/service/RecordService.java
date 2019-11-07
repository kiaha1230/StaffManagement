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

import com.team3.customModel.AccountCustom;
import com.team3.customModel.RecordCustom;
import com.team3.model.Depart;
import com.team3.model.Record;
import com.team3.repository.RecordRepository;

@Service
public class RecordService {
	@Autowired
	private RecordRepository recordRepository;
	@PersistenceContext
	private EntityManager em;

	public ArrayList<Record> getAllRecord() {
		return (ArrayList<Record>) recordRepository.findAll();
	}

	public Optional<Record> getById(Integer id) {
		return recordRepository.findById(id);
	}

	public void addOrEditRecord(Record record) {
		recordRepository.save(record);

	}

	public void deleteRecord(Integer id) {
		recordRepository.deleteById(id);
	}

	public ArrayList<Record> findByCondition(Record record) {
		ArrayList<Record> list = new ArrayList<Record>();
		String query = "select r.id, r.type,r.reason,r.createDate, s.staffName from Record r , Staff s where r.staffId = s.id ";
		if (!(record.getReason() == null)) {
			query += " and  r.reason like :reason";
		}
		if (!(record.getStaffId() == null)) {
			query += " and s.id = :staffId  ";
		}
		if (record.isType() != null) {
			query += " and r.type = :type ";
		}
		Query q = em.createQuery(query);
		if (!(record.getReason() == null)) {
			q.setParameter("reason", record.getReason());
		}
		if (!(record.getStaffId() == null)) {
			q.setParameter("staffId", record.getStaffId());
		}
		if (record.isType() != null) {
			q.setParameter("type", record.isType());
		}
		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((records) -> {
			Record custom = new Record();
			custom.setId((Integer) records[0]);
			custom.setType((Boolean) records[1]);
			custom.setReason((String) records[2]);
			custom.setCreateDate((Date) records[3]);
			custom.setStaffName(records[4].toString());
			list.add(custom);
		});
		return list;
	}

}

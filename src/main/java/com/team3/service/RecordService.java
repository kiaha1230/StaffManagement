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

import com.team3.Ultilities.Ultilities;
import com.team3.bean.Mailer;
import com.team3.customModel.AccountCustom;
import com.team3.customModel.RecordCustom;
import com.team3.model.APIResponse;
import com.team3.model.Depart;
import com.team3.model.Pager;
import com.team3.model.Record;
import com.team3.model.Staff;
import com.team3.repository.RecordRepository;
import com.team3.repository.StaffRepository;

@Service
public class RecordService {
	@Autowired
	private RecordRepository recordRepository;
	@PersistenceContext
	private EntityManager em;
	@Autowired
	Mailer mailer;
	@Autowired
	private StaffRepository staffRepository;

	public ArrayList<Record> getAllRecord() {
		return (ArrayList<Record>) recordRepository.findAll();
	}

	public Optional<Record> getById(Integer id) {
		return recordRepository.findById(id);
	}

	public void addOrEditRecord(Record record) {
		recordRepository.save(record);
		sendMail(record);
	}

	public void deleteRecord(Integer id) {
		recordRepository.deleteById(id);
	}

//	public ArrayList<Record> findByCondition(Record record) {
//		ArrayList<Record> list = new ArrayList<Record>();
//		String query = "select r.id, r.type,r.reason,r.createDate, s.staffName,r.staffId from Record r , Staff s where r.staffId = s.id ";
//		if (!(record.getReason() == null)) {
//			query += " and  r.reason like :reason";
//		}
//		if (!(record.getStaffId() == null)) {
//			query += " and s.id = :staffId  ";
//		}
//		if (record.isType() != null) {
//			query += " and r.type = :type ";
//		}
//		if (record.getFromCreateDate() != null && record.getToCreateDate() != null) {
//			query += " and r.createDate between :fromDate and :toDate ";
//		}
//		if (record.getFromCreateDate() != null && record.getToCreateDate() == null) {
//			query += " and r.createDate >= :fromDate ";
//		}
//		if (record.getFromCreateDate() == null && record.getToCreateDate() != null) {
//			query += " and r.createDate <= :toDate ";
//		}
//		Query q = em.createQuery(query);
//		if (!(record.getReason() == null)) {
//			q.setParameter("reason", "%" + record.getReason() + "%");
//		}
//		if (!(record.getStaffId() == null)) {
//			q.setParameter("staffId", record.getStaffId());
//		}
//		if (record.isType() != null) {
//			q.setParameter("type", record.isType());
//		}
//		if (record.getFromCreateDate() != null && record.getToCreateDate() != null) {
//			q.setParameter("fromDate", record.getFromCreateDate());
//			q.setParameter("toDate", record.getToCreateDate());
//		}
//		if (record.getFromCreateDate() != null && record.getToCreateDate() == null) {
//			q.setParameter("fromDate", record.getFromCreateDate());
//		}
//		if (record.getFromCreateDate() == null && record.getToCreateDate() != null) {
//			q.setParameter("toDate", record.getToCreateDate());
//		}
//		List<Object[]> obj = q.getResultList();
//		obj.stream().forEach((records) -> {
//			Record custom = new Record();
//			custom.setId((Integer) records[0]);
//			custom.setType((Boolean) records[1]);
//			custom.setReason((String) records[2]);
//			custom.setCreateDate((Date) records[3]);
//			custom.setStaffName(records[4].toString());
//			custom.setStaffId((Integer) records[5]);
//			list.add(custom);
//		});
//		return list;
//	}

	// API

	public APIResponse findByCondition(Record record) {
		ArrayList<Record> list = new ArrayList<Record>();
		String query = "select r.id, r.type,r.reason,r.createDate, s.staffName,r.staffId,r.bonus from Record r , Staff s where r.staffId = s.id ";
		if (!(record.getReason() == null)) {
			query += " and  r.reason like :reason";
		}
		if (!(record.getStaffId() == null)) {
			query += " and s.id = :staffId  ";
		}
		if (record.isType() != null) {
			query += " and r.type = :type ";
		}
		if (record.getFromCreateDate() != null && record.getToCreateDate() != null) {
			query += " and r.createDate between :fromDate and :toDate ";
		}
		if (record.getFromCreateDate() != null && record.getToCreateDate() == null) {
			query += " and r.createDate >= :fromDate ";
		}
		if (record.getFromCreateDate() == null && record.getToCreateDate() != null) {
			query += " and r.createDate <= :toDate ";
		}
		Query q = em.createQuery(query);
		if (!(record.getReason() == null)) {
			q.setParameter("reason", "%" + record.getReason() + "%");
		}
		if (!(record.getStaffId() == null)) {
			q.setParameter("staffId", record.getStaffId());
		}
		if (record.isType() != null) {
			q.setParameter("type", record.isType());
		}
		if (record.getFromCreateDate() != null && record.getToCreateDate() != null) {
			q.setParameter("fromDate", record.getFromCreateDate());
			q.setParameter("toDate", record.getToCreateDate());
		}
		if (record.getFromCreateDate() != null && record.getToCreateDate() == null) {
			q.setParameter("fromDate", record.getFromCreateDate());
		}
		if (record.getFromCreateDate() == null && record.getToCreateDate() != null) {
			q.setParameter("toDate", record.getToCreateDate());
		}

		APIResponse response = new APIResponse();
		Pager pager = new Pager();
		List<Object[]> totalRow = q.getResultList();
		pager.setTotalRow(totalRow.size());
		q.setFirstResult(record.getPager().getPage() * record.getPager().getPageSize());
		q.setMaxResults(record.getPager().getPageSize());

		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((records) -> {
			Record custom = new Record();
			custom.setId((Integer) records[0]);
			custom.setType((Boolean) records[1]);
			custom.setReason((String) records[2]);
			custom.setCreateDate((Date) records[3]);
			custom.setStaffName(records[4].toString());
			custom.setStaffId((Integer) records[5]);
			custom.setBonus((Double) records[6]);
			list.add(custom);
		});

		pager.setPageSize(record.getPager().getPageSize());
		pager.setPage(record.getPager().getPage());
		response.setPager(pager);
		response.setData(list);
		return response;
	}

	public void sendMail(Record record) {
		Optional<Staff> staff = staffRepository.findById(record.getStaffId());
		String to = staff.get().getEmail();
		String strDate = Ultilities.dateToString(new Date());
		String from = "Vissoft";
		String subject = "Khen thưởng và kỉ luật";
		String loai = "";
		String body = "";
		if (record.getType() == true) {
			loai = "Khen thưởng";
			body += "- Bạn có một " + loai.toUpperCase() + " vào ngày " + strDate;
			body += "\n - Lí do : " + record.getReason();
			body += "\n - Thưởng : " + record.getBonus();

		} else {
			loai = "Kỉ luật";
			body += "- Bạn có một " + loai.toUpperCase() + " vào ngày " + strDate;
			body += "\n - Lí do : " + record.getReason();
			body += "\n - Phạt : " + record.getBonus();
		}

		mailer.send(from, to, subject, body);

	}

	public List<Record> getByStaffId(Integer staffId, Integer month, Integer year) {
		List<Record> records = new ArrayList<Record>();
		String hql = "From Record where staffId = :staffId and MONTH(createDate) = :month and YEAR(createDate) = :year";
		Query q = em.createQuery(hql);
		q.setParameter("staffId", staffId);
		q.setParameter("month", month);
		q.setParameter("year", year);
		records = q.getResultList();
		if (records == null) {
			return null;
		}
		return records;
	}

}

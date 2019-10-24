package com.team3.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.team3.customModel.StaffCustom;
import com.team3.model.Record;
import com.team3.model.Staff;
import com.team3.repository.StaffRepository;

@Service
public class StaffService {

	@Autowired
	private StaffRepository staffRepository;
	@PersistenceContext
	private EntityManager em;

	public ArrayList<Staff> getAllStaff() {
		List<Staff> list = new ArrayList<Staff>();
		list = staffRepository.findAll();
		return (ArrayList<Staff>) list;
	}

	public Optional<Staff> getById(Integer id) {
		return staffRepository.findById(id);
	}

	public void addOrEditStaff(Staff staff) {
		staffRepository.save(staff);

	}

	public void deleteStaff(Integer id) {
		staffRepository.deleteById(id);
	}

	public ArrayList<StaffCustom> findByCondition(Staff staff) {
		ArrayList<StaffCustom> list = new ArrayList<StaffCustom>();
		String query = "select s.id, s.staffCode,s.staffName,d.departName, s.gender,s.birthday,s.photo, s.email,s.phone,s.status,s.role from Depart d , Staff s where d.id = s.departId ";
		if (!(staff.getStaffCode() == null)) {
			query += " and  r.staffCode like :staffCode";
		}
		if (!(staff.getStaffName() == null)) {
			query += " and s.staffName = :staffName ";
		}
		if (staff.getDepartId() != null) {
			query += " and s.departId = :departId ";
		}
		if (staff.isStatus() != null) {
			query += " and s.status = :status ";
		}
		Query q = em.createQuery(query);
		if (!(staff.getStaffCode() == null)) {
			q.setParameter("staffCode", staff.getStaffCode());
		}
		if (!(staff.getStaffName() == null)) {
			q.setParameter("staffName", staff.getStaffName());
		}
		if (staff.getDepartId() != null) {
			q.setParameter("departId", staff.getDepartId());
		}
		if (staff.isStatus() != null) {
			q.setParameter("status", staff.isStatus());
		}
		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((staffs) -> {
			StaffCustom custom = new StaffCustom();
			custom.setId((Integer) staffs[0]);
			custom.setStaffCode((String) staffs[1]);
			custom.setStaffName((String) staffs[2]);
			custom.setDepartName((String) staffs[3]);
			custom.setGender((Boolean) staffs[4]);
			custom.setBirthday((Date) staffs[5]);
			custom.setPhoto((String) staffs[6]);
			custom.setEmail((String) staffs[7]);
			custom.setPhoto((String) staffs[8]);
			custom.setStatus((Boolean) staffs[9]);
			list.add(custom);
		});
		return list;
	}

}

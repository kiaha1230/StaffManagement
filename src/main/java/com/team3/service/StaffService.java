package com.team3.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.checkerframework.checker.units.qual.s;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.team3.customModel.StaffCustom;
import com.team3.model.APIResponse;
import com.team3.model.Depart;
import com.team3.model.Pager;
import com.team3.model.Record;
import com.team3.model.Salary;
import com.team3.model.Staff;
import com.team3.repository.StaffRepository;

@Service
public class StaffService {

	@Autowired
	private StaffRepository staffRepository;
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private LogAuditService auditService;

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

	public ArrayList<Staff> top10Staff() {
		ArrayList<Staff> list = new ArrayList<Staff>();
		String hql = "SELECT  s.staffName, SUM(case when r.type=1 then 1 else 0 end), SUM(case when r.type=0 then 1 else 0 end),SUM(case when r.type=1 then 1 else 0 end) - SUM(case when r.type=0 then 1 else 0 end)"
				+ "  FROM Record r,Staff s where r.staffId = s.id "
				+ "GROUP BY s.staffName order by (SUM(case when r.type=1 then 1 else 0 end) - SUM(case when r.type=0 then 1 else 0 end)) desc";
		Query q = em.createQuery(hql);
		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((staffs) -> {
			Staff custom = new Staff();
			custom.setStaffName((String) staffs[0]);
			custom.setKhenThuong((Long) staffs[1]);
			custom.setKiLuat((Long) staffs[2]);
			custom.setDiem((Long) staffs[3]);
			list.add(custom);
		});
		return list;
	}

	public ArrayList<Staff> getByActive() {
		ArrayList<Staff> list = new ArrayList<Staff>();
		String hql = "from Staff where status = 1";
		Query q = em.createQuery(hql);
		list = (ArrayList<Staff>) q.getResultList();
		return list;
	}

	public ArrayList<Staff> getsStaffWithoutAccount() {
		ArrayList<Staff> list = new ArrayList<Staff>();
		String hql = "from Staff s where s.id not in ( select s.id from Account a, Staff s where a.staffId = s.id) ";
		Query q = em.createQuery(hql);
		list = (ArrayList<Staff>) q.getResultList();
		return list;
	}

	public ArrayList<Staff> getStaffHaveBirthday() {
		Date currentDate = new Date();
		ArrayList<Staff> list = new ArrayList<Staff>();
		String hql = " from  Staff where MONTH(BIRTHDAY) = MONTH(:currentDate)";
		Query q = em.createQuery(hql);
		q.setParameter("currentDate", currentDate);
		list = (ArrayList<Staff>) q.getResultList();
		return list;
	}

	public ArrayList<Staff> getsStaffWithoutSalary() {
		ArrayList<Staff> list = new ArrayList<Staff>();
		String hql = "from Staff s where s.id not in ( select s.id from Salary sa, Staff s where sa.staffId = s.id) ";
		Query q = em.createQuery(hql);
		list = (ArrayList<Staff>) q.getResultList();
		return list;
	}

	public ArrayList<Staff> getsStaffWithoutAllowance() {
		ArrayList<Staff> list = new ArrayList<Staff>();
		String hql = "from Staff s where s.id not in ( select s.id from Allowance a, Staff s where a.staffId = s.id) ";
		Query q = em.createQuery(hql);
		list = (ArrayList<Staff>) q.getResultList();
		return list;
	}

	// API

	public APIResponse findByCondition(Staff staff) {
		ArrayList<Staff> list = new ArrayList<Staff>();
		String query = "select s.id, s.staffCode,s.staffName,d.departName, s.gender,s.birthday,s.photo, s.email,s.phoneNumber,s.status,s.departId,p.positionName,s.positionId, s.address from Depart d , Staff s,Position p where d.id = s.departId and p.id = s.positionId  ";
		if (!(staff.getStaffCode() == null)) {
			query += " and  s.staffCode like :staffCode";
		}
		if (!(staff.getStaffName() == null)) {
			query += " and s.staffName like :staffName ";
		}
		if (staff.getDepartId() != null) {
			query += " and s.departId = :departId ";
		}
		if (staff.isStatus() != null) {
			query += " and s.status = :status ";
		}
		query += " order by status desc";
		Query q = em.createQuery(query);
		if (!(staff.getStaffCode() == null)) {
			q.setParameter("staffCode", "%" + staff.getStaffCode() + "%");
		}
		if (!(staff.getStaffName() == null)) {
			q.setParameter("staffName", "%" + staff.getStaffName() + "%");
		}
		if (staff.getDepartId() != null) {
			q.setParameter("departId", staff.getDepartId());
		}
		if (staff.isStatus() != null) {
			q.setParameter("status", staff.isStatus());
		}

		APIResponse response = new APIResponse();
		Pager pager = new Pager();
		List<Object[]> totalRow = q.getResultList();
		pager.setTotalRow(totalRow.size());
		q.setFirstResult(staff.getPager().getPage() * staff.getPager().getPageSize());
		q.setMaxResults(staff.getPager().getPageSize());

		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((staffs) -> {
			Staff custom = new Staff();
			custom.setId((Integer) staffs[0]);
			custom.setStaffCode((String) staffs[1]);
			custom.setStaffName((String) staffs[2]);
			custom.setDepartName((String) staffs[3]);
			custom.setGender((Boolean) staffs[4]);
			custom.setBirthday((Date) staffs[5]);
			custom.setPhoto((String) staffs[6]);
			custom.setEmail((String) staffs[7]);
			custom.setPhoneNumber((String) staffs[8]);
			custom.setStatus((Boolean) staffs[9]);
			custom.setDepartId((Integer) staffs[10]);
			custom.setPositionName((String) staffs[11]);
			custom.setPositionId((Integer) staffs[12]);
			custom.setAddress((String) staffs[13]);
			list.add(custom);
		});

		pager.setPageSize(staff.getPager().getPageSize());
		pager.setPage(staff.getPager().getPage());
		response.setPager(pager);
		response.setData(list);
		return response;
	}

	public Boolean checkStaffCodeDuplicate(String staffCode) {
		Staff staff = new Staff();
		String hql = " from Staff where staffCode = :staffCode  ";
		Query q = em.createQuery(hql);
		q.setParameter("staffCode", staffCode);
		staff = (Staff) q.getResultList().stream().findFirst().orElse(null);
		if (staff == null) {
			return true;
		} else {
			return false;
		}

	}

	public Staff getbyIdHQL(Integer id) {
		Staff staff = new Staff();
		String hql = "From Staff where id = :id";
		Query q = em.createQuery(hql);
		q.setParameter("id", id);
		staff = (Staff) q.getResultList().stream().findFirst().orElse(null);
		return staff;

	}

}

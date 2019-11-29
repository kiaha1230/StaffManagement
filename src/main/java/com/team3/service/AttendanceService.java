package com.team3.service;

import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.aspectj.weaver.reflect.Java14GenericSignatureInformationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.Ultilities.Ultilities;
import com.team3.model.APIResponse;
import com.team3.model.Allowance;
import com.team3.model.Attendance;
import com.team3.model.Pager;
import com.team3.repository.AttendanceRepository;

@Service
public class AttendanceService {
	@Autowired
	private AttendanceRepository attendanceRepository;
	@PersistenceContext
	private EntityManager em;

	public ArrayList<Attendance> getAllAttendance() {
		List<Attendance> list = new ArrayList<Attendance>();
		list = attendanceRepository.findAll();
		return (ArrayList<Attendance>) list;
	}

	public Optional<Attendance> getById(Integer id) {
		return attendanceRepository.findById(id);
	}

	public void addAttendance(Attendance attendance) {
		attendanceRepository.save(attendance);
	}

	public void editAttendance(Attendance attendance) {
		attendanceRepository.save(attendance);
	}

	public void deleteAttendance(int id) {
		attendanceRepository.deleteById(id);
	}

//	public ArrayList<Attendance> getByCondition(Attendance attendance) {
//		ArrayList<Attendance> list = new ArrayList<Attendance>();
//		String query = "select a.id , s.staffName , a.attendanceDate , a.checkinTime, a.checkoutTime from Attendance a , Staff s where a.staffId = s.id ";
//		if (attendance.getStaffId() != null) {
//			query += " and  a.staffId = :staffId ";
//		}
//		if (attendance.getToAttendanceDate() != null && attendance.getFromAttendanceDate() != null) {
//			query += " and a.attendanceDate between :fromAttendanceDate and :toAttendanceDate ";
//		}
//		if (attendance.getToAttendanceDate() != null && attendance.getFromAttendanceDate() == null) {
//			query += " and a.attendanceDate <= :toAttendanceDate ";
//		}
//		if (attendance.getToAttendanceDate() == null && attendance.getFromAttendanceDate() != null) {
//			query += " and a.attendanceDate >= :fromAttendanceDate ";
//		}
//
//		Query q = em.createQuery(query);
//		if (attendance.getStaffId() != null) {
//			q.setParameter("staffId", attendance.getStaffId());
//		}
//		if (attendance.getToAttendanceDate() != null && attendance.getFromAttendanceDate() != null) {
//			q.setParameter("fromAttendanceDate", attendance.getFromAttendanceDate());
//			q.setParameter("toAttendanceDate", attendance.getToAttendanceDate());
//		}
//		if (attendance.getToAttendanceDate() != null && attendance.getFromAttendanceDate() == null) {
//			q.setParameter("toAttendanceDate", attendance.getToAttendanceDate());
//		}
//		if (attendance.getToAttendanceDate() == null && attendance.getFromAttendanceDate() != null) {
//			q.setParameter("fromAttendanceDate", attendance.getFromAttendanceDate());
//		}
//
//		List<Object[]> obj = q.getResultList();
//		obj.stream().forEach((record) -> {
//			Attendance custom = new Attendance();
//			custom.setId((Integer) record[0]);
//			custom.setStaffName(record[1].toString());
//			custom.setAttendanceDate((Date) record[2]);
//			custom.setCheckInTime((String) record[3]);
//			custom.setCheckOutTime((String) record[4]);
//			list.add(custom);
//		});
//		return list;
//	}

	// API

	public APIResponse getByCondition(Attendance attendance) {
		ArrayList<Attendance> list = new ArrayList<Attendance>();
		String query = "select a.id , s.staffName , a.attendanceDate , a.checkinTime, a.checkoutTime,a.staffId from Attendance a , Staff s where a.staffId = s.id ";
		if (attendance.getStaffId() != null) {
			query += " and  a.staffId = :staffId ";
		}
		if (attendance.getToAttendanceDate() != null && attendance.getFromAttendanceDate() != null) {
			query += " and a.attendanceDate between :fromAttendanceDate and :toAttendanceDate ";
		}
		if (attendance.getToAttendanceDate() != null && attendance.getFromAttendanceDate() == null) {
			query += " and a.attendanceDate <= :toAttendanceDate ";
		}
		if (attendance.getToAttendanceDate() == null && attendance.getFromAttendanceDate() != null) {
			query += " and a.attendanceDate >= :fromAttendanceDate ";
		}

		Query q = em.createQuery(query);
		if (attendance.getStaffId() != null) {
			q.setParameter("staffId", attendance.getStaffId());
		}
		if (attendance.getToAttendanceDate() != null && attendance.getFromAttendanceDate() != null) {
			q.setParameter("fromAttendanceDate", attendance.getFromAttendanceDate());
			q.setParameter("toAttendanceDate", attendance.getToAttendanceDate());
		}
		if (attendance.getToAttendanceDate() != null && attendance.getFromAttendanceDate() == null) {
			q.setParameter("toAttendanceDate", attendance.getToAttendanceDate());
		}
		if (attendance.getToAttendanceDate() == null && attendance.getFromAttendanceDate() != null) {
			q.setParameter("fromAttendanceDate", attendance.getFromAttendanceDate());
		}

		APIResponse response = new APIResponse();
		Pager pager = new Pager();
		List<Object[]> totalRow = q.getResultList();
		pager.setTotalRow(totalRow.size());
		q.setFirstResult(attendance.getPager().getPage() * attendance.getPager().getPageSize());
		q.setMaxResults(attendance.getPager().getPageSize());

		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((record) -> {
			Attendance custom = new Attendance();
			custom.setId((Integer) record[0]);
			custom.setStaffName(record[1].toString());
			custom.setAttendanceDate((Date) record[2]);
			custom.setCheckinTime((Time) record[3]);
			custom.setCheckoutTime((Time) record[4]);
			custom.setStaffId((Integer) record[5]);
			list.add(custom);
		});

		pager.setPageSize(attendance.getPager().getPageSize());
		pager.setPage(attendance.getPager().getPage());
		response.setPager(pager);
		response.setData(list);
		return response;
	}

	public Attendance getByStaffId(Integer staffId) {
		Attendance custom = new Attendance();
		String query = "select * from Attendance where STAFF_ID = :staffId and ATTENDANCE_DATE = :date";
		Query q = em.createNativeQuery(query);
		q.setParameter("staffId", staffId);
		q.setParameter("date", Ultilities.dateToStringUSFormat(new Date()));
		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((record) -> {
			custom.setId((Integer) record[0]);
			custom.setStaffId((Integer) record[1]);
			custom.setAttendanceDate((Date) record[2]);
			custom.setCheckinTime((Time) record[3]);
			custom.setCheckoutTime((Time) record[4]);
		});

		return custom;
	}

	public void checkIn(Integer staffId) {
		Attendance attendance = new Attendance();
		attendance.setAttendanceDate(new Date());
		Time time = new Time(new Date().getTime());
		attendance.setCheckinTime(time);
		attendance.setStaffId(staffId);
		attendanceRepository.save(attendance);
	}

	public void checkOut(Integer staffId) {
		Attendance attendance = new Attendance();
		attendance = getByStaffId(staffId);
		if (attendance != null) {
			Time time = new Time(new Date().getTime());
			attendance.setCheckoutTime(time);
			attendanceRepository.save(attendance);
		}
	}

	public Integer getWorkingDayOfStaff(Integer staffId, Integer month, Integer year) {
		List<Attendance> list = new ArrayList<Attendance>();
		String query = "from Attendance where staffId = :staffId and checkoutTime is not null and MONTH(attendanceDate) = :month and YEAR(attendanceDate) = :year";
		Query q = em.createQuery(query);
		q.setParameter("staffId", staffId);
		q.setParameter("month", month);
		q.setParameter("year", year);
		list = q.getResultList();
		return list.size();

	}

}

package com.team3.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.model.Attendance;

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

	public Optional<Attendance> getById(int id) {
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

	public ArrayList<Attendance> getByCondition(Attendance attendance) {
		ArrayList<Attendance> list = new ArrayList<Attendance>();
		String query = "select a.id , s.staffName , a.attendanceDate , a.checkInTime, a.checkOutTime from Attendance a , Staff s where a.staffId = s.id ";
		if (attendance.getStaffId() != null) {
			query += " and  a.staffId = :staffId ";
		}
		if (attendance.getToDate() != null && attendance.getFromDate() != null) {
			query += " and a.attendaceDate between :fromDate and :toDate ";
		}
		if (attendance.getToDate() != null && attendance.getFromDate() == null) {
			query += " and a.attendaceDate >= :fromDate ";
		}
		if (attendance.getToDate() == null && attendance.getFromDate() != null) {
			query += " and a.attendaceDate <= :fromDate ";
		}
		Query q = em.createQuery(query);
		if (attendance.getStaffId() != null) {
			q.setParameter("staffId", attendance.getStaffId());
		}
		if (attendance.getToDate() != null && attendance.getFromDate() != null) {
			q.setParameter("fromDate", attendance.getFromDate());
			q.setParameter("toDate", attendance.getToDate());
		}
		if (attendance.getToDate() != null && attendance.getFromDate() == null) {
			q.setParameter("fromDate", attendance.getFromDate());
		}
		if (attendance.getToDate() == null && attendance.getFromDate() != null) {
			q.setParameter("toDate", attendance.getToDate());
		}

		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((record) -> {
			Attendance custom = new Attendance();
			custom.setId((Integer) record[0]);
			custom.setStaffName(record[1].toString());
			custom.setAttendanceDate((Date) record[2]);
			custom.setCheckInTime((Time) record[3]);
			custom.setCheckOutTime((Time) record[4]);
			list.add(custom);
		});
		return list;
	}

}

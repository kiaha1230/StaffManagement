package com.team3.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.team3.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

}

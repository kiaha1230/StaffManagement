package com.team3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team3.model.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {

}

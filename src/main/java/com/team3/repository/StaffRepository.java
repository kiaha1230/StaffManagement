package com.team3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team3.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

}

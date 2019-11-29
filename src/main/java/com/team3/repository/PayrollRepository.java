package com.team3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team3.model.Payroll;

public interface PayrollRepository extends JpaRepository<Payroll, Integer> {

}

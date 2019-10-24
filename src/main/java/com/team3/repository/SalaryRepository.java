package com.team3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team3.model.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {

}

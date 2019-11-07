package com.team3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team3.model.LogAudit;


public interface LogAuditRepository  extends JpaRepository<LogAudit, Integer> {

}

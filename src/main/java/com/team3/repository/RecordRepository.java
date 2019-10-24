package com.team3.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.team3.model.Record;

public interface RecordRepository extends JpaRepository<Record, Integer> {

}

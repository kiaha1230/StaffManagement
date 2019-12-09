package com.team3;

import java.util.Date;
import java.util.Timer;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.team3.service.RecordService;

@SpringBootApplication
public class StaffManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaffManagementApplication.class, args);
//		Date date = new Date();
//		RecordService recordService = new RecordService();
//		Timer timer = new Timer();
//		timer.schedule(recordService.doEvery23h(), date, period);

	}

}

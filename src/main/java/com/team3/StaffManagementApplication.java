package com.team3;

import java.time.Period;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.team3.service.RecordService;

@SpringBootApplication
public class StaffManagementApplication {
	@Autowired
	private RecordService recordService;

	public static void main(String[] args) {
		SpringApplication.run(StaffManagementApplication.class, args);
//		Date date = new Date();
//		date.setHours(9);
//		date.setMinutes(47);
//		RecordService recordService = new RecordService();
//		Timer timer = new Timer();
//		TimerTask task = new TimerTask() {
//			@Override
//			public void run() {
//				try {
//					recordService.doEvery23h();
//					System.out.println("23h task run succesfully");
//				} catch (Exception e) {
//					System.out.println("23h task  failed");
//				}
//
//			}
//		};
//		timer.schedule(task, date, 86400000);

	}

}

package com.team3;

import java.time.Period;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.CacheControl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.team3.service.RecordService;

@SpringBootApplication
@EnableScheduling
public class StaffManagementApplication {
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//		registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/images/")
//				.setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
//	}

	public static void main(String[] args) {
		SpringApplication.run(StaffManagementApplication.class, args);
	}

}

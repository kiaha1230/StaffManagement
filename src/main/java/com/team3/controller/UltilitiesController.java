package com.team3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team3.model.Account;
import com.team3.service.UltilitiesService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/ulti")
public class UltilitiesController {
	@Autowired
	UltilitiesService service;
}

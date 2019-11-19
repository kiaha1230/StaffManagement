package com.team3.Ultilities;

import org.springframework.beans.factory.annotation.Autowired;

import com.team3.model.LogAudit;
import com.team3.model.LogDetail;
import com.team3.service.LogAuditService;
import com.team3.service.LogDetailService;

public class LogFunction {
	@Autowired
	private LogAuditService logAudit;
	@Autowired
	private LogDetailService logDetail;
	

	

}

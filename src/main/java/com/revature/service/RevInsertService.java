package com.revature.service;

import java.math.BigDecimal;
import java.util.Date;

import com.revature.dao.RevInsertDAO;
import com.revature.dao.impl.RevInsertDAOImpl;
import com.revature.exception.BusinessException;
import com.revature.model.ReimbTicket;

//Service layer for any logic that needs to apply to requests
public class RevInsertService {
	
	RevInsertDAO revInsertDAO = new RevInsertDAOImpl();

	public boolean createTicket(int userId, BigDecimal reimbAmount, String reimbDescription, int reimbTypeId) throws BusinessException {
		
		boolean createTicket = false;
		
		//checking validity of request data
		if (reimbAmount.floatValue() > 0) {
			
			ReimbTicket reimbTicket = new ReimbTicket(reimbAmount, new Date(), reimbDescription, userId, 1, reimbTypeId);
			createTicket = revInsertDAO.insertTicket(reimbTicket);
		}
		
		return createTicket;
		
	}


}

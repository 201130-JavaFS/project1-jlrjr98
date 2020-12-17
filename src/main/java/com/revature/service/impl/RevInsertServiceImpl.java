package com.revature.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import com.revature.dao.RevInsertDAO;
import com.revature.dao.impl.RevInsertDAOImpl;
import com.revature.exception.BusinessException;
import com.revature.model.ReimbTicket;
import com.revature.service.RevInsertService;
import com.revature.service.RevSearchService;

public class RevInsertServiceImpl implements RevInsertService{
	
	RevSearchService revSearchService = new RevSearchServiceImpl();
	
	RevInsertDAO revInsertDAO = new RevInsertDAOImpl();

	@Override
	public boolean createTicket(int userId, BigDecimal reimbAmount, String reimbDescription, int reimbTypeId) throws BusinessException {
		
		
		
		ReimbTicket reimbTicket = new ReimbTicket(reimbAmount, new Date(), reimbDescription, userId, 1, reimbTypeId);
		
		
		return revInsertDAO.insertTicket(reimbTicket);
		
	}

}

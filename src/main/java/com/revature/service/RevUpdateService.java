package com.revature.service;

import java.util.Date;

import com.revature.dao.RevUpdateDAO;
import com.revature.dao.impl.RevUpdateDAOImpl;
import com.revature.exception.BusinessException;
import com.revature.service.RevUpdateService;

public class RevUpdateService {
	
	private RevUpdateDAO revUpdateDAO = new RevUpdateDAOImpl();
	
	public boolean sendReview(int reimbId, int reimbStatusId, int reimbResolver) throws BusinessException {
		
		return revUpdateDAO.updateReimbStatusIdAndReimbResolverAndReimbResolvedByReimbId(reimbId, reimbStatusId, reimbResolver, new Date());		
	}

}

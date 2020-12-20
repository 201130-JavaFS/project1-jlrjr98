package com.revature.service;

import com.revature.exception.BusinessException;

public interface RevUpdateService {

	public boolean sendReview(int reimbId, int reimbStatusId, int reimbResolver) throws BusinessException;
	
}

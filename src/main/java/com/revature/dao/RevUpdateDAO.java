package com.revature.dao;

import java.util.Date;

import com.revature.exception.BusinessException;

public interface RevUpdateDAO {
	
	boolean updateReimbStatusIdAndReimbResolverAndReimbResolvedByReimbId(int reimbId, int reimbStatusId, int reimbResolver, Date reimbResolved) throws BusinessException;

}

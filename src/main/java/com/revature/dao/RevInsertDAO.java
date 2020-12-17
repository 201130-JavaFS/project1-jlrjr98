package com.revature.dao;

import com.revature.exception.BusinessException;
import com.revature.model.ReimbTicket;

public interface RevInsertDAO {

	public boolean insertTicket(ReimbTicket reimbTicket) throws BusinessException;

}

package com.revature.service;

import java.math.BigDecimal;

import com.revature.exception.BusinessException;

public interface RevInsertService {

	public boolean createTicket(int userId, BigDecimal reimbAmount, String reimbDescription, int reimbTypeId) throws BusinessException;
}

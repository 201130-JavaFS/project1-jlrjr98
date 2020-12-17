package com.revature.service;

import java.util.List;

import com.revature.exception.BusinessException;
import com.revature.model.Employee;
import com.revature.model.ReimbTicket;

public interface RevSearchService {
	
//	public Employee getEmployee(String username) throws BusinessException;
	public Employee login(String username, String password) throws BusinessException;
	public List<ReimbTicket> getUserTickets(int userId) throws BusinessException;

}

package com.revature.dao;

import java.util.List;

import com.revature.exception.BusinessException;
import com.revature.model.Employee;
import com.revature.model.ReimbTicket;

public interface RevSearchDAO {
	
	//public Employee getUserByUsername(String username) throws BusinessException;
//	public String getUsernameByUsernameAndPassword(String username, String password) throws BusinessException;
	public List<ReimbTicket> getTicketsByUserId(int userId) throws BusinessException;
	public Employee getUserByUsernameAndPassword(String username, String password) throws BusinessException;
}

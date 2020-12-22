package com.revature.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.RevSearchDAO;
import com.revature.dao.impl.RevSearchDAOImpl;
import com.revature.exception.BusinessException;
import com.revature.model.Employee;
import com.revature.model.ReimbTicket;

public class RevSearchService {
	
	RevSearchDAO revSearchDAO = new RevSearchDAOImpl();
	
	public Employee login(String username, String password) throws BusinessException {
		
		Employee employee = null;
		employee = revSearchDAO.getUserByUsernameAndPassword(username, password);
		return employee;
	}

	public List<ReimbTicket> getUserTickets(int userId) throws BusinessException {
		
		List<ReimbTicket> userTicketList = null;
		userTicketList = revSearchDAO.getTicketsByUserId(userId);
		return userTicketList;
		
	}

	public List<ReimbTicket> getAllTickets(int userId) throws BusinessException {
		
		List<ReimbTicket> listOfAllTickets = null;
		listOfAllTickets = revSearchDAO.getAllTicketsExceptById(userId);
		return listOfAllTickets;
	}

}

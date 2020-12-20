package com.revature.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.RevSearchDAO;
import com.revature.dao.impl.RevSearchDAOImpl;
import com.revature.exception.BusinessException;
import com.revature.model.Employee;
import com.revature.model.ReimbTicket;
import com.revature.service.RevSearchService;

public class RevSearchServiceImpl implements RevSearchService {
	
	private static final Logger log = LogManager.getLogger(RevSearchServiceImpl.class); 
	
	RevSearchDAO revSearchDAO = new RevSearchDAOImpl();
	
	@Override
	public Employee login(String username, String password) throws BusinessException {
		
		//String verifiedUsername = revSearchDAO.getUsernameByUsernameAndPassword(username, password);
		

		Employee employee = revSearchDAO.getUserByUsernameAndPassword(username, password);

		return employee;
	}
	
	@Override
	public List<ReimbTicket> getUserTickets(int userId) throws BusinessException {
		
		List<ReimbTicket> userTicketList = null;
		
		userTicketList = revSearchDAO.getTicketsByUserId(userId);
		
		return userTicketList;
		
	}

	@Override
	public List<ReimbTicket> getAllTickets(int userId) throws BusinessException {
		
		List<ReimbTicket> listOfAllTickets = null;
		
		listOfAllTickets = revSearchDAO.getAllTicketsExceptById(userId);
		
		return listOfAllTickets;
	}

}

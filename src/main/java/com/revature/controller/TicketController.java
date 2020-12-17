package com.revature.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exception.BusinessException;
import com.revature.model.DTO.TicketDTO;
import com.revature.service.RevInsertService;
import com.revature.service.RevSearchService;
import com.revature.service.impl.RevInsertServiceImpl;
import com.revature.service.impl.RevSearchServiceImpl;

public class TicketController {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private RevSearchService revSearchService = new RevSearchServiceImpl();
	private RevInsertService revInsertService = new RevInsertServiceImpl();

	private static final Logger log = LogManager.getLogger(TicketController.class); 
	
	public void getUserTickets(HttpServletRequest req, HttpServletResponse res) throws IOException, BusinessException {
		
	
		
		log.debug("in TicketController");
		
		if (req.getMethod().equals("POST")) {
		
			BufferedReader bufferedReader = req.getReader();
			StringBuilder bodyBuilder = new StringBuilder();
			String line = bufferedReader.readLine();
			
			
			while (line != null) {
				bodyBuilder.append(line);
				line = bufferedReader.readLine();
			}
			
			String body = new String(bodyBuilder);
			TicketDTO ticketDTO = objectMapper.readValue(body, TicketDTO.class);
			
			if (revSearchService.getUserTickets(ticketDTO.userId) != null) {
				
				HttpSession httpSession = req.getSession();
				httpSession.setAttribute("userId", ticketDTO);
				httpSession.setAttribute("acquiredTickets", true);
				
				res.setStatus(202);

			} else {
				
				HttpSession httpSession = req.getSession(false);
				
				if (httpSession != null) {
					httpSession.invalidate();
				}
				
				res.setStatus(204);
				res.getWriter().print("No Information");
			}
			
			
		}
		
	}

	public void createTicket(HttpServletRequest req, HttpServletResponse res) throws IOException, BusinessException {
		
		if (req.getMethod().equals("POST")) {
			
			BufferedReader bufferedReader = req.getReader();
			StringBuilder bodyBuilder = new StringBuilder();
			String line = bufferedReader.readLine();
			
			
			while (line != null) {
				bodyBuilder.append(line);
				line = bufferedReader.readLine();
			}
			
			String body = new String(bodyBuilder);
			TicketDTO ticketDTO = objectMapper.readValue(body, TicketDTO.class);
			
			if (revInsertService.createTicket(ticketDTO.userId, ticketDTO.reimbAmount, ticketDTO.reimbDescription, ticketDTO.reimbTypeId)) {
				
			}
			
		}
	}
	
}

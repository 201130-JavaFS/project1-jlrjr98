package com.revature.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exception.BusinessException;
import com.revature.model.ReimbTicket;
import com.revature.model.DTO.TicketDTO;
import com.revature.service.RevInsertService;
import com.revature.service.RevSearchService;
import com.revature.service.RevUpdateService;
import com.revature.service.impl.RevInsertServiceImpl;
import com.revature.service.impl.RevSearchServiceImpl;
import com.revature.service.impl.RevUpdateServiceImpl;

public class TicketController {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private RevSearchService revSearchService = new RevSearchServiceImpl();
	private RevInsertService revInsertService = new RevInsertServiceImpl();
	private RevUpdateService revUpdateService = new RevUpdateServiceImpl();

	private static final Logger log = LogManager.getLogger(TicketController.class); 
	
	public void getUserTickets(HttpServletRequest req, HttpServletResponse res) throws IOException, BusinessException {
		
		log.info("in TicketController getUserTickets");
		
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
			
			log.info("about to enter Service");
			
			
			List<ReimbTicket> ticketList  = revSearchService.getUserTickets(ticketDTO.userId);
			
			log.info("just left Service");
			
			if (ticketList != null && !(ticketList.isEmpty())) {
				
				HttpSession httpSession = req.getSession();
				httpSession.setAttribute("userId", ticketDTO);
				httpSession.setAttribute("acquiredTickets", true);
				
				String json = objectMapper.writeValueAsString(ticketList);
				res.getWriter().print(json);
				
				res.setStatus(202);

			} else if (ticketList != null && ticketList.isEmpty()){
				
				HttpSession httpSession = req.getSession();
				httpSession.setAttribute("userId", ticketDTO);
				httpSession.setAttribute("acquiredTickets", false);
				
				res.setStatus(204);
				res.getWriter().print("No Information");
				
			} else {
				
				HttpSession httpSession = req.getSession(false);
				
				if (httpSession != null) {
					httpSession.invalidate();
				}
				
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
				
				res.setStatus(201);
				res.getWriter().print("Ticket Creation Success");
				
			} else {
				res.setStatus(401);
				res.getWriter().print("Ticket Creation Failure");
			}
			
		}
	}

	public void getAllTickets(HttpServletRequest req, HttpServletResponse res) throws IOException, BusinessException {
		
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
			
			List<ReimbTicket> listOfAllTickets = revSearchService.getAllTickets(ticketDTO.userId);
			
			if (listOfAllTickets != null && !(listOfAllTickets.isEmpty())) {
				
				HttpSession httpSession = req.getSession();
				
				//if (httpSession != null) {
				
				httpSession.setAttribute("acquiredAllTickets", true);
				
				res.setStatus(200);
				
				String json = objectMapper.writeValueAsString(listOfAllTickets);
				res.getWriter().print(json);
				
				//}
				
			} else if (listOfAllTickets != null && listOfAllTickets.isEmpty()){
							
//				HttpSession httpSession = req.getSession();
//				httpSession.setAttribute("userId", ticketDTO);
				HttpSession httpSession = req.getSession();
				httpSession.setAttribute("acquiredAllTickets", false);
				
				res.setStatus(204);
				res.getWriter().print("No Information");
							
			} else {
				
				HttpSession httpSession = req.getSession(false);
				
				if (httpSession != null) {
					httpSession.invalidate();
				}
			}
			
		} else {
			
			res.setStatus(404);
		}
		
	}

	public void sendReview(HttpServletRequest req, HttpServletResponse res) throws IOException, BusinessException {

		if (req.getMethod().equals("PATCH")) {
			
			BufferedReader bufferedReader = req.getReader();
			StringBuilder bodyBuilder = new StringBuilder();
			String line = bufferedReader.readLine();
			
			
			while (line != null) {
				bodyBuilder.append(line);
				line = bufferedReader.readLine();
			}
			String body = new String(bodyBuilder);
			TicketDTO ticketDTO = objectMapper.readValue(body, TicketDTO.class);
			
			if (revUpdateService.sendReview(ticketDTO.reimbId, ticketDTO.reimbStatusId, ticketDTO.reimbResolver)) {		
				
				HttpSession httpSession = req.getSession();
				httpSession.setAttribute("acquiredAllTickets", true);
				
				res.setStatus(200);
				
			}
			
		}
		
	}
	
}

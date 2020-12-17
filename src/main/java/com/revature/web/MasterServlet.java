package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.controller.EmployeeController;
import com.revature.controller.LoginController;
import com.revature.controller.TicketController;
import com.revature.exception.BusinessException;
import com.revature.service.RevSearchService;
import com.revature.service.impl.RevSearchServiceImpl;

public class MasterServlet extends HttpServlet {	
	
//	public static Logger log = Logger.getLogger(MasterServlet.class);
	private static final Logger log = LogManager.getLogger(MasterServlet.class); 

	private static final long serialVersionUID = 1L;
	
	RevSearchService revSearchService = new RevSearchServiceImpl();
	
	private LoginController loginController = new LoginController();
	private EmployeeController employeeController = new EmployeeController();
	private TicketController ticketController = new TicketController();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.info("Server is Started");
		
		res.setContentType("application/json");
		res.setStatus(404);
		
		final String URI = req.getRequestURI().replace("/project-1/", "");
		
		switch (URI) {
			
			case "login": {
				
				try {
					
					log.debug("About to enter LoginController");
					
					loginController.login(req, res);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (BusinessException e) {
					e.printStackTrace();
				}
				
				break;
			}
			
			case "home": {
				
//				try {
//					
//					log.debug("About to enter employee controller");
//					
//					employeeController.getEmployee(req, res);
//				} catch (IOException e) {
//					e.printStackTrace();
//				} catch (BusinessException e) {
//					e.printStackTrace();
//				}
				
				
				break;
			}
			case "view-tickets": {
				
				try {
					log.debug("About to enter employee controller");
					
					ticketController.getUserTickets(req, res);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (BusinessException e) {
					e.printStackTrace();
				}
				
				break;
			}
			case "create-ticket": {
				
				try {
					ticketController.createTicket(req, res);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (BusinessException e) {
					e.printStackTrace();
				}
				
				break;
			}
			case "review-tickets": {
				
				break;
			}
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.debug("in doPost");
		
		doGet(req, res);
		
		
	}

}

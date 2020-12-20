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
import com.revature.controller.SessionController;
import com.revature.controller.SignoutController;
import com.revature.controller.SignoutController;
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
	private SignoutController signoutController = new SignoutController();
	private EmployeeController employeeController = new EmployeeController();
	private TicketController ticketController = new TicketController();
	private SessionController sessionController = new SessionController();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.info("Server is Started");
		
		res.setContentType("application/json");
		res.setStatus(404);
		
		final String URI = req.getRequestURI().replace("/project-1/", "");
		
		switch (URI) {
			
			case "login": 
				
				try {
					
					log.debug("About to enter LoginController");
					
					loginController.login(req, res);
				} catch (IOException | BusinessException e) {
					e.printStackTrace();
				} 
				
				break;
			
			
			case "home": 
				
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
			
			case "view-tickets": 
				
				try {
					log.debug("About to enter employee controller");
					
					ticketController.getUserTickets(req, res);
				} catch (IOException | BusinessException e) {
					e.printStackTrace();
				}
				
				break;
			
			case "create-ticket": 

				try {
					ticketController.createTicket(req, res);
				} catch (IOException | BusinessException e) {
					e.printStackTrace();
				}
				
				break;
			
			case "review-tickets": 
				
				try {
					ticketController.getAllTickets(req, res);
				} catch (IOException | BusinessException e1) {
					e1.printStackTrace();
				}
					
					break;
			case "send-review":
				
			try {
				ticketController.sendReview(req, res);
			} catch (IOException | BusinessException e1) {
				e1.printStackTrace();
			}
				
				break;
			
			case "signout": 
				
				try {
					signoutController.signout(req, res);
				} catch (IOException | BusinessException e) {
					e.printStackTrace();
				}
				
				break;
			
			case "check-session":
				
				try {
					sessionController.checkSession(req, res);
				} catch (IOException | BusinessException e) {
					e.printStackTrace();
				}
					
				break;
				
			case "create-ticket-page":
				
				res.setStatus(200);
				
				break;
				
			default:
				break;
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.debug("in doPost");
		
		doGet(req, res);
		
	}
     
    public void doPatch(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
 
    	doGet(req, res);
    }

	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("PATCH")){
           doPatch(req, res);
        } else {
            super.service(req, res);
        }
    }
    
}

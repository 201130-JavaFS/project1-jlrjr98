package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.controller.LoginController;
import com.revature.controller.SessionController;
import com.revature.controller.SignoutController;
import com.revature.controller.TicketController;
import com.revature.exception.BusinessException;

public class MasterServlet extends HttpServlet {	
	 
	private static final Logger log = LogManager.getLogger(MasterServlet.class); 

	private static final long serialVersionUID = 1L;
	
	private LoginController loginController = new LoginController();
	private SignoutController signoutController = new SignoutController();
	private TicketController ticketController = new TicketController();
	private SessionController sessionController = new SessionController();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.info("in doGet");
		
		res.setContentType("application/json");
		res.setStatus(404);
		
		final String URI = req.getRequestURI().replace("/project-1/", "");
		
		switch (URI) {
			
			case "login": 
				
				try {
					loginController.login(req, res);
				} catch (IOException | BusinessException e) {
					e.printStackTrace();
					log.error("Runtime Error: ", e);
				} 
				
				break;
			
			case "view-tickets": 
				
				try {					
					ticketController.getUserTickets(req, res);
				} catch (IOException | BusinessException e) {
					e.printStackTrace();
					log.error("Runtime Error: ", e);
				}
				
				break;
			
			case "create-ticket": 

				try {
					ticketController.createTicket(req, res);
				} catch (IOException | BusinessException e) {
					e.printStackTrace();
					log.error("Runtime Error: ", e);
				}
				
				break;
			
			case "review-tickets": 
				
				try {
					ticketController.getAllTickets(req, res);
				} catch (IOException | BusinessException e1) {
					e1.printStackTrace();
					log.error("Runtime Error: ", e1);
				}
					
					break;
			case "send-review":
				
				try {
					ticketController.sendReview(req, res);
				} catch (IOException | BusinessException e1) {
					e1.printStackTrace();
					log.error("Runtime Error: ", e1);;
				}
					
				break;
			
			case "signout": 
				
				try {
					signoutController.signout(req, res);
				} catch (IOException e) {
					e.printStackTrace();
					log.error("Runtime Error: ", e);
				}
				
				break;
			
			case "check-session":
				
				try {
					sessionController.checkSession(req, res);
				} catch (IOException e) {
					e.printStackTrace();
					log.error("Runtime Error: ", e);
				}
					
				break;
				
			default:
				
				break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.info("in doPost");
		
		doGet(req, res);
		
	}
     
    public void doPatch(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
 
    	log.info("in doPatch");
    	
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

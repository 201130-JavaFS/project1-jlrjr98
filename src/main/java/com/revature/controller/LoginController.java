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
import com.revature.model.Employee;
import com.revature.model.DTO.LoginDTO;
import com.revature.service.RevSearchService;
import com.revature.service.impl.RevSearchServiceImpl;

public class LoginController {
	
	private static final Logger log = LogManager.getLogger(LoginController.class); 
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private RevSearchService revSearchService = new RevSearchServiceImpl();

	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException, BusinessException {
		
		log.debug("in LoginController login");
		
		if (req.getMethod().equals("POST")) {
			
			BufferedReader bufferedReader = req.getReader();
			StringBuilder bodyBuilder = new StringBuilder();
			String line = bufferedReader.readLine();
			
			while (line != null)
			{
				bodyBuilder.append(line);
				line = bufferedReader.readLine();
			}
			
			String body = new String(bodyBuilder);
			LoginDTO loginDTO = objectMapper.readValue(body, LoginDTO.class);
			
			log.debug("About to Enter Service");
			
			Employee employee = revSearchService.login(loginDTO.username, loginDTO.password);
			
			if (employee != null) {
				
				log.debug("Exiting Service");
				
				HttpSession httpSession = req.getSession();
				
				httpSession.setAttribute("user", loginDTO);
				httpSession.setAttribute("loggedIn", true);
				
				String json = objectMapper.writeValueAsString(employee);
				res.getWriter().print(json);
				
				res.setStatus(202);
				//res.getWriter().print("Loading Successful");		
				
			} else {
				HttpSession httpSession = req.getSession(false);
				
				if (httpSession != null) {
					httpSession.invalidate();
				}
				
				res.setStatus(401);
				res.getWriter().print("Login Failed");
				
			}

		} 
		
	}
	
	

}

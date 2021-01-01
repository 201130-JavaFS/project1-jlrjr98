package com.revature.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exception.BusinessException;
import com.revature.model.Employee;
import com.revature.model.DTO.LoginDTO;
import com.revature.security.Encrypt;
import com.revature.service.RevSearchService;

//Controllers used to get data out of request objects, invoke service methods, and put data into response objects
public class LoginController {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private RevSearchService revSearchService = new RevSearchService();

	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException, BusinessException {
		
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
			//DTO used to hold data form requests
			//readValue() is a Jackson method used to change JSON into a Java object
			LoginDTO loginDTO = objectMapper.readValue(body, LoginDTO.class);
			
			String encryptedPassword = Encrypt.encryptPassword(loginDTO.password);
			
			//sent to service layer
			Employee employee = revSearchService.login(loginDTO.username, encryptedPassword);
			
			if (employee != null) {
				
				HttpSession httpSession = req.getSession();
				
				httpSession.setAttribute("user", loginDTO);
				httpSession.setAttribute("loggedIn", true);
				
				//writeValueAssString() is a Jackson method to turn Java object into JSON
				String json = objectMapper.writeValueAsString(employee);
				res.getWriter().print(json);
				//change HTTP status to that of success
				res.setStatus(202);		
				
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

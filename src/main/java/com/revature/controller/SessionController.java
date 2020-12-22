package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionController {
	
	public void checkSession(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		HttpSession httpSession = req.getSession(false);
		
		if (httpSession != null) {
			res.getWriter().print("logged in");
		} 
		res.setStatus(200);
	}
}

package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignoutController {

	public void signout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		HttpSession httpSession = req.getSession(false);
		httpSession.invalidate();
		
		res.setStatus(200);
		res.getWriter().print("Successful Signout");
	}
	
}

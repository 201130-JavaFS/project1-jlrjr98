package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.exception.BusinessException;

public class SignoutController {
	
	private static final Logger log = LogManager.getLogger(SignoutController.class); 

	public void signout(HttpServletRequest req, HttpServletResponse res) throws IOException, BusinessException {
		
		log.info("Entering signout");
		
		HttpSession httpSession = req.getSession();
		httpSession.invalidate();
		
		res.setStatus(200);
		res.getWriter().print("Successful Signout");
		
	}
	
}

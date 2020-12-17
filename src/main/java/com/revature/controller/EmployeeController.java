package com.revature.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.service.RevSearchService;
import com.revature.service.impl.RevSearchServiceImpl;

public class EmployeeController {
	
	private static final Logger log = LogManager.getLogger(EmployeeController.class); 
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private RevSearchService revSearchService = new RevSearchServiceImpl();

//	public void getEmployee(HttpServletRequest req, HttpServletResponse res) throws IOException, BusinessException {
//		
//		log.debug("in LoginController getEmployee");
//		
//		if (req.getMethod().equals("POST")) {
//			
//			BufferedReader bufferedReader = req.getReader();
//			StringBuilder bodyBuilder = new StringBuilder();
//			String line = bufferedReader.readLine();
//			
//			while (line != null)
//			{
//				bodyBuilder.append(line);
//				line = bufferedReader.readLine();
//			}
//			
//			String body = new String(bodyBuilder);
//			EmployeeDTO employeeDTO = objectMapper.readValue(body, EmployeeDTO.class);
//			
//			log.debug("About to Enter Service");
//			
//			Employee employee = revSearchService.getEmployee(employeeDTO.username);
//			
//			if (employee != null) {
//				
//				log.debug("Exiting Service");
//				
//				HttpSession httpSession = req.getSession();
//				
//				httpSession.setAttribute("user", employeeDTO);
//				httpSession.setAttribute("acquiredInfo", true);								
//				
//				String json = objectMapper.writeValueAsString(employee);
//				res.getWriter().print(json);
//				
//				res.setStatus(202);
//				//res.getWriter().print("Loading Successful");
//				
//				
//			} else {
//				HttpSession httpSession = req.getSession(false);
//				
//				if (httpSession != null) {
//					httpSession.invalidate();
//				}
//				
//				res.setStatus(204);
//				res.getWriter().print("No Information");
//				
//			}
//
//		}
//	}
	
}

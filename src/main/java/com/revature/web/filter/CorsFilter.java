package com.revature.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Filter to prevent Cors Errors
public class CorsFilter implements Filter {
	
	private static final Logger log = LogManager.getLogger(CorsFilter.class); 

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		log.debug("CORS Filter leveraged");
		
		if(!(response instanceof HttpServletResponse)) {
			chain.doFilter(request, response);
			return;
		}
		
		HttpServletResponse res = (HttpServletResponse) response;
		
		res.setHeader("Access-Control-Allow-Origin", "null");
		
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD");
		
		res.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type,"
				+ "Access-Control-Request-Method, Access-Control-Request-Headers");
		
		res.setHeader("Access-Control-Allow-Credentials", "true");
		
		chain.doFilter(request, response);
	}

}

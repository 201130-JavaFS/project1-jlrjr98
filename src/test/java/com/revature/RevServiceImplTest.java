package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import com.revature.exception.BusinessException;
import com.revature.model.Employee;
import com.revature.model.ReimbTicket;
import com.revature.service.RevSearchService;
import com.revature.service.RevUpdateService;

@ExtendWith(CustomParameterResolver.class)
class RevServiceImplTest {

	private static RevSearchService revSearchService;
	private static RevUpdateService revUpdateService;

	@BeforeAll
	public static void setUpSearchService() {
		revSearchService = new RevSearchService();
		revUpdateService = new RevUpdateService();
	}

	@Test
	void testLogin(Server server, Server server1) throws BusinessException {

		String username = "1punchman";
		String password = "408c26081aae1335849aa0b1dc6742f99e2b7e3d";

		Employee actual = revSearchService.login(username, password);
		Employee expected = new Employee(2, "1punchman", "Sai", "Tama", "hero4fun@gmail.com", 2, "Trainer");

		boolean b = expected.equals(actual);

		assertEquals(true, b);
	}

	@Test
	void testGetUserTickets(Server server) throws BusinessException {

		int userId = 1;

		List<ReimbTicket> actual = revSearchService.getUserTickets(userId);

		String reimbResolvedString = null;
		Blob reimbReciept = null;
		int reimbResolver = 0;
		BigDecimal reimbAmount = new BigDecimal("50.58");
		
		List<ReimbTicket> expected = new ArrayList<ReimbTicket>();
		ReimbTicket reimbTicket = new ReimbTicket(6, reimbAmount, "2020-12-17 16:39:43", reimbResolvedString,
				"Laser eye implant", reimbReciept, 1, reimbResolver, "pending", "Other");
		expected.add(reimbTicket);
		
		System.out.println(actual);
		System.out.println(expected);

		boolean b = expected.equals(actual);

		assertEquals(true, b);

	}

	@Test
	void testGetAllTickets(Server server) throws BusinessException {

		int userId = 1;
		List<ReimbTicket> actual = revSearchService.getAllTickets(userId);

		List<ReimbTicket> notExpected = new ArrayList<ReimbTicket>();
		String reimbResolvedString = null;
		Blob reimbReciept = null;
		int reimbResolver = 0;
		BigDecimal reimbAmount = new BigDecimal("980.70");
		ReimbTicket reimbTicket = new ReimbTicket(7, reimbAmount, "2020-12-20 06:16:04", reimbResolvedString,
				"Fell down a flight of stairs.", reimbReciept, 3, reimbResolver, "pending", "Medical");
		notExpected.add(reimbTicket);

		boolean b = notExpected.equals(actual);

		assertEquals(false, b);
	}

	@Test
	void testSendReview(Server server, Server server1, Server server2) throws BusinessException {

		boolean actual = revUpdateService.sendReview(5, 2, 1);
		boolean expected = true;

		assertEquals(expected, actual);

	}

}

class Server {
	private String host = "http://dev-dev/";
	private String endpoint = "people";

	public String getHost() {
		return host;
	}

	public String getEndpoint() {
		return endpoint;
	}
}

class CustomParameterResolver implements ParameterResolver {
	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		return (parameterContext.getParameter().getType() == Server.class);
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		return new Server();
	}
}
package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import com.revature.service.RevInsertService;
import com.revature.service.RevSearchService;
import com.revature.service.RevUpdateService;
import com.revature.service.impl.RevInsertServiceImpl;
import com.revature.service.impl.RevSearchServiceImpl;
import com.revature.service.impl.RevUpdateServiceImpl;

@ExtendWith(CustomParameterResolver.class)
public class RevServiceImplTest {

	private static RevSearchService revSearchService;
	private static RevUpdateService revUpdateService;
	private static RevInsertService revInsertService;

	@BeforeAll
	public static void setUpSearchService() {
		revSearchService = new RevSearchServiceImpl();
		revUpdateService = new RevUpdateServiceImpl();
		revInsertService = new RevInsertServiceImpl();
	}

	@Test
	void testLogin(Server server, Server server1) throws BusinessException {

		String username = "1punchman";
		String password = "e4d916fa9d2e6d4c0d993976bd68952dd00add15ca1289b32750280b3f3cd45f";

		Employee actual = revSearchService.login(username, password);
		Employee expected = new Employee(2, "1punchman", "Sai", "Tama", "hero4fun@gmail.com", 2, "Trainer");

		boolean b = expected.equals(actual);

		assertEquals(true, b);
	}

	@Test
	void testGetUserTickets(Server server) throws BusinessException {

		int userId = 3;

		List<ReimbTicket> actual = revSearchService.getUserTickets(userId);

		String reimbResolvedString = null;
		Blob reimbReciept = null;
		int reimbResolver = 0;
		BigDecimal reimbAmount = new BigDecimal("980.70");
		List<ReimbTicket> expected = new ArrayList();
		ReimbTicket reimbTicket = new ReimbTicket(7, reimbAmount, "2020-12-20 06:16:04", reimbResolvedString,
				"Fell down a flight of stairs.", reimbReciept, 3, reimbResolver, 1, "pending", 3, "Medical");
		expected.add(reimbTicket);

		boolean b = expected.equals(actual);

		assertEquals(true, b);

	}

	@Test
	void testGetAllTickets(Server server) throws BusinessException {

		int userId = 1;
		List<ReimbTicket> actual = revSearchService.getAllTickets(userId);

		List<ReimbTicket> notExpected = new ArrayList();
		String reimbResolvedString = null;
		Blob reimbReciept = null;
		int reimbResolver = 0;
		BigDecimal reimbAmount = new BigDecimal("980.70");
		ReimbTicket reimbTicket = new ReimbTicket(7, reimbAmount, "2020-12-20 06:16:04", reimbResolvedString,
				"Fell down a flight of stairs.", reimbReciept, 3, reimbResolver, 1, "pending", 3, "Medical");
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

	@Test
	void testCreateTicket(Server server, Server server1, Server server2, Server server3) throws BusinessException {

		BigDecimal reimbAmount = new BigDecimal("-10.22");

		Exception exception = assertThrows(BusinessException.class, () -> {
			boolean actual = revInsertService.createTicket(1, reimbAmount, "test", 1);
		});

		String expectedMessage = "Reimbusement Amount less than 0.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

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
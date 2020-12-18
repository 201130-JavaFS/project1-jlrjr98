package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.RevSearchDAO;
import com.revature.dao.dbutil.RevConnection;
import com.revature.dao.dbutil.queries.RevSearchQueries;
import com.revature.exception.BusinessException;
import com.revature.model.Employee;
import com.revature.model.ReimbTicket;

public class RevSearchDAOImpl implements RevSearchDAO {
	
	private static final Logger log = LogManager.getLogger(RevSearchDAOImpl.class); 

	@Override
	public Employee getUserByUsernameAndPassword(String username, String password) throws BusinessException {
		
		Employee employee = null;
		
		log.debug("In DAO. About to Enter Database.");
		
		
		try (Connection connection = RevConnection.getConnection()) {
			
			String sql = RevSearchQueries.GET_USER_BY_USERNAME_AND_PASSWORD;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			
			if (resultSet.next()) {
				
				employee = new Employee(
						resultSet.getInt("ers_users_id"),
						resultSet.getString("ers_username"), 
						resultSet.getString("user_first_name"), 
						resultSet.getString("user_last_name"), 
						resultSet.getString("user_email"),
						resultSet.getInt("user_role_id"),
						resultSet.getString("user_role"));
			}
			
			if (username.equals(employee.getUsername())) {
				return employee;
			}
			
		} catch (NullPointerException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
//	@Override
//	public String getUsernameByUsernameAndPassword(String username, String password) throws BusinessException {
//		
//		String verifiedUsername = null;
//
//		try (Connection connection = RevConnection.getConnection()) {
//
//			String sql = RevSearchQueries.GET_USERNAME_BY_USERNAME_AND_PASSWORD;
//			PreparedStatement preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1, username);
//			preparedStatement.setString(2, password);
//			
//			ResultSet resultSet = preparedStatement.executeQuery();
//			
//			if (resultSet.next()) {
//				verifiedUsername = resultSet.getString("ers_username");
//			}
//			
//		} catch (ClassNotFoundException | SQLException e) {
//
//			e.printStackTrace();
//		}
//		
//		if (verifiedUsername.equals(username)) {
//			return verifiedUsername;
//		}
//		
//		return verifiedUsername;
//		
//	}
	
	@Override
	public List<ReimbTicket> getTicketsByUserId(int userId) throws BusinessException {
		
		
		try (Connection connection = RevConnection.getConnection()) {
			
			String sql = RevSearchQueries.GET_TICKETS_BY_USER_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			List<ReimbTicket> userTicketList = new ArrayList<>();
			
			while (resultSet.next()) {
				ReimbTicket reimbTicket = new ReimbTicket(
						resultSet.getInt("reimb_id"),
						resultSet.getBigDecimal("reimb_amount"),
//						resultSet.getTimestamp("reimb_submitted"),
//						resultSet.getTimestamp("reimb_resolved"),
						resultSet.getString("reimb_submitted"),
						resultSet.getString("reimb_resolved"),
						resultSet.getString("reimb_description"),
						resultSet.getBlob("reimb_reciept"),
						resultSet.getInt("reimb_author"),
						resultSet.getInt("reimb_resolver"),
						resultSet.getInt("reimb_status_id"),
						resultSet.getString("reimb_status"),
						resultSet.getInt("reimb_type_id"),
						resultSet.getString("reimb_type"));
				
				userTicketList.add(reimbTicket);
			}
			
			return userTicketList;
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<ReimbTicket> getAllTicketsExceptById(int userId) throws BusinessException {
		
		try (Connection connection = RevConnection.getConnection()) {
			
			String sql = RevSearchQueries.GET_ALL_TICKETS_EXCEPT_BY_USER_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			List<ReimbTicket> listOfAllTickets = new ArrayList<>();
			
			while (resultSet.next()) {
				ReimbTicket reimbTicket;
					reimbTicket = new ReimbTicket(
							resultSet.getInt("reimb_id"),
							resultSet.getBigDecimal("reimb_amount"),
							resultSet.getString("reimb_submitted"),
							resultSet.getString("reimb_resolved"),
							resultSet.getString("reimb_description"),
							resultSet.getBlob("reimb_reciept"),
							resultSet.getInt("reimb_author"),
							resultSet.getInt("reimb_resolver"),
							resultSet.getInt("reimb_status_id"),
							resultSet.getString("reimb_status"),
							resultSet.getInt("reimb_type_id"),
							resultSet.getString("reimb_type"));
					
					listOfAllTickets.add(reimbTicket);
					
			}
			
			return listOfAllTickets;
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}		

		return null;
	}
}

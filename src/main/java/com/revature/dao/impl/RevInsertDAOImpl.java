package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.dao.RevInsertDAO;
import com.revature.dao.dbutil.RevConnection;
import com.revature.dao.dbutil.queries.RevInsertQueries;
import com.revature.exception.BusinessException;
import com.revature.model.ReimbTicket;

public class RevInsertDAOImpl implements RevInsertDAO {
	
	@Override
	public boolean insertTicket(ReimbTicket reimbTicket) throws BusinessException {
		
		try (Connection connection = RevConnection.getConnection()) {
			
			String sql = RevInsertQueries.INSERT_REIMB_TICKET;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBigDecimal(1, reimbTicket.getReimbAmount());
			preparedStatement.setTimestamp(2, new java.sql.Timestamp(reimbTicket.getReimbSubmitted().getTime()));
			preparedStatement.setString(3, reimbTicket.getReimbDescription());
			preparedStatement.setInt(4, reimbTicket.getReimbAuthor());
			preparedStatement.setInt(5, reimbTicket.getReimbStatusId());
			preparedStatement.setInt(6, reimbTicket.getReimbTypeId());
			
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}

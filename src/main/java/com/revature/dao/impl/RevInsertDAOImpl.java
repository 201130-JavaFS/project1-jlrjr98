package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.RevInsertDAO;
import com.revature.dao.dbutil.RevConnection;
import com.revature.dao.dbutil.queries.RevInsertQueries;
import com.revature.exception.BusinessException;
import com.revature.model.ReimbTicket;

//DAO layer is used to retrieve/insert data from or into the database
public class RevInsertDAOImpl implements RevInsertDAO {
	
	private static final Logger log = LogManager.getLogger(RevInsertDAOImpl.class);
	
	@Override
	public boolean insertTicket(ReimbTicket reimbTicket) throws BusinessException {		
		
		//try with resources is used to automatically close connection afterwards
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
			log.error("Runtime Error: ", e);
			
			return false;
		}
		
		return true;
	}

}

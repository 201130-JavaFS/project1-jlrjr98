package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.RevUpdateDAO;
import com.revature.dao.dbutil.RevConnection;
import com.revature.dao.dbutil.queries.RevUpdateQueries;
import com.revature.exception.BusinessException;

public class RevUpdateDAOImpl implements RevUpdateDAO {
	
	private static final Logger log = LogManager.getLogger(RevUpdateDAOImpl.class); 

	@Override
	public boolean updateReimbStatusIdAndReimbResolverAndReimbResolvedByReimbId(int reimbId, int reimbStatusId, int reimbResolver, Date reimbResolved) throws BusinessException {
		
		try (Connection connection = RevConnection.getConnection()) {
			
			String sql = RevUpdateQueries.UPDATE_REIMB_STATUS_ID_AND_REIMB_RESOLVER_AND_REIMB_RESOLVED_BY_REIMB_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, reimbStatusId);
			preparedStatement.setInt(2, reimbResolver);
			preparedStatement.setTimestamp(3, new java.sql.Timestamp(reimbResolved.getTime()));
			preparedStatement.setInt(4, reimbId);
			
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			log.error("Runtime Error: ", e);
			return false;
		}
		
		return true;
	}
}

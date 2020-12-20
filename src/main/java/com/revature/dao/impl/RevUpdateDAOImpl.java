package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.revature.dao.RevUpdateDAO;
import com.revature.dao.dbutil.RevConnection;
import com.revature.dao.dbutil.queries.RevUpdateQueries;
import com.revature.exception.BusinessException;
import com.revature.model.ReimbTicket;

public class RevUpdateDAOImpl implements RevUpdateDAO {

	@Override
	public boolean updateReimbStatusIdAndReimbResolverAndReimbResolvedByReimbId(int reimbId, int reimbStatusId, int reimbResolver, Date reimbResolved) throws BusinessException {

		ReimbTicket reimbTicket = new ReimbTicket();
		
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
			return false;
		}
		
		return true;
	}

}

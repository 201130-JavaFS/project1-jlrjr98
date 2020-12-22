package com.revature.dao.dbutil.queries;

public class RevInsertQueries {
	
	private RevInsertQueries() {}

	public static final String INSERT_REIMB_TICKET = ""
			+ "INSERT INTO ers.ers_reimbursement (reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_status_id, reimb_type_id) "
			+ "VALUES (?, ?, ?, ?, ?, ?);";
	
}

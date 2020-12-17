package com.revature.dao.dbutil.queries;

public class RevInsertQueries {

	public static final String INSERT_REIMB_TICKET = ""
			+ "INSERT INTO ers.reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_reciept, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	
	//don't insert status and type. They are already there.
	
	//createReimbTicket

}

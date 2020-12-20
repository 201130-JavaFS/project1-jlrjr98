package com.revature.dao.dbutil.queries;

public class RevUpdateQueries {
	
	//updateReimbStatus
	public static final String UPDATE_REIMB_STATUS_ID_AND_REIMB_RESOLVER_AND_REIMB_RESOLVED_BY_REIMB_ID = ""
			+ "UPDATE ers.ers_reimbursement "
			+ "SET reimb_status_id = ?, reimb_resolver = ?, reimb_resolved = ? "
			+ "WHERE reimb_id = ?;";

}

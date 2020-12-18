package com.revature.dao.dbutil.queries;

public class RevSearchQueries {

//	public static final String GET_USER_BY_USERNAME = ""
//			+ "SELECT ers_users_id, ers_username, user_first_name, user_last_name, user_email, user_role_id, user_role "
//			+ "FROM ers.ers_users "
//			+ "JOIN ers.ers_user_roles "
//			+ "ON ers.ers_users.user_role_id = ers.ers_user_roles.ers_user_role_id "
//			+ "WHERE ers_username = ?;";
	
	public static final String GET_USER_BY_USERNAME_AND_PASSWORD = ""
			+ "SELECT ers_users_id, ers_username, user_first_name, user_last_name, user_email, user_role_id, user_role "
			+ "FROM ers.ers_users "
			+ "JOIN ers.ers_user_roles "
			+ "ON ers.ers_users.user_role_id = ers.ers_user_roles.ers_user_role_id "
			+ "WHERE ers_username = ? AND ers_password = ?;";

	public static final String GET_TICKETS_BY_USER_ID = ""
			+ "SELECT reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_reciept, reimb_author, reimb_resolver, ers_reimbursement.reimb_status_id, reimb_status, ers_reimbursement.reimb_type_id, reimb_type "
			+ "FROM ers.ers_reimbursement "
			+ "INNER JOIN ers.ers_reimbursement_status ON ers_reimbursement.reimb_status_id = ers_reimbursement_status.reimb_status_id "
			+ "INNER JOIN ers.ers_reimbursement_type ON ers_reimbursement.reimb_type_id = ers_reimbursement_type.reimb_type_id "
			+ "WHERE reimb_author = ?;";
	
	public static final String GET_ALL_TICKETS_EXCEPT_BY_USER_ID = ""
			+ "SELECT reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_reciept, reimb_author, reimb_resolver, ers_reimbursement.reimb_status_id, reimb_status, ers_reimbursement.reimb_type_id, reimb_type "
			+ "FROM ers.ers_reimbursement "
			+ "INNER JOIN ers.ers_reimbursement_status ON ers_reimbursement.reimb_status_id = ers_reimbursement_status.reimb_status_id "
			+ "INNER JOIN ers.ers_reimbursement_type ON ers_reimbursement.reimb_type_id = ers_reimbursement_type.reimb_type_id "
			+ "WHERE reimb_author != ?;";
	
	
	//ers_reimbursement.reimb_author
	
	//getReimbTickets
	
	//getReimbRequests
	//getReimbRequestsByStatus //view pending

}

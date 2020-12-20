package com.revature.model.DTO;

import java.math.BigDecimal;

public class TicketDTO {

	public int userId;
	public BigDecimal reimbAmount;
	public String reimbDescription;
	public int reimbTypeId;
	
	public int reimbId;
	public int reimbStatusId;
	public int reimbResolver;

	public TicketDTO() {
		super();
	}

	public TicketDTO(int userId, BigDecimal reimbAmount, String reimbDescription, int reimbTypeId) {
		super();
		this.userId = userId;
		this.reimbAmount = reimbAmount;
		this.reimbDescription = reimbDescription;
		this.reimbTypeId = reimbTypeId;
	}

	public TicketDTO(int reimbId, int reimbStatusId, int reimbResolver) {
		super();
		this.reimbId = reimbId;
		this.reimbStatusId = reimbStatusId;
		this.reimbResolver = reimbResolver;
	}

	public TicketDTO(int userId) {
		super();
		this.userId = userId;
	}
}

package com.revature.model.DTO;

import java.math.BigDecimal;

public class TicketDTO {

	public int userId;
	public BigDecimal reimbAmount;
	public String reimbDescription;
	public int reimbTypeId;

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

	
	
}

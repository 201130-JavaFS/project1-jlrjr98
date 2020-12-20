package com.revature.model;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;

public class ReimbTicket {
	
	private int reimbId;
	private BigDecimal reimbAmount;
	private Date reimbSubmitted;
	private Date reimbResolved;
	private String reimbDescription;
	private Blob reimbReciept;
	private int reimbAuthor;
	private int reimbResolver;
	private int reimbStatusId;
	private String reimbStatus;
	private int reimbTypeId;
	private String reimbType;
	
	
	private String reimbSubmittedString;
	private String reimbResolvedString;
	
	public ReimbTicket() {
		super();
	}
	
	public ReimbTicket(BigDecimal reimbAmount, Date reimbSubmitted, String reimbDescription, int reimbAuthor,
			int reimbStatusId, int reimbTypeId) {
		super();
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbDescription = reimbDescription;
		this.reimbAuthor = reimbAuthor;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
	}

	
	//given when employee requests tickets
//	public ReimbTicket(int reimbId, BigDecimal reimbAmount, Date reimbSubmitted, Date reimbResolved,
//			String reimbDescription, Blob reimbReciept, int reimbAuthor, int reimbResolver, int reimbStatusId,
//			String reimbStatus, int reimbTypeId, String reimbType) {
//		super();
//		this.reimbId = reimbId;
//		this.reimbAmount = reimbAmount;
//		this.reimbSubmitted = reimbSubmitted;
//		this.reimbResolved = reimbResolved;
//		this.reimbDescription = reimbDescription;
//		this.reimbReciept = reimbReciept;
//		this.reimbAuthor = reimbAuthor;
//		this.reimbResolver = reimbResolver;
//		this.reimbStatusId = reimbStatusId;
//		this.reimbStatus = reimbStatus;
//		this.reimbTypeId = reimbTypeId;
//		this.reimbType = reimbType;
//	}
	
	public ReimbTicket(int reimbId, BigDecimal reimbAmount, String reimbSubmittedString, String reimbResolvedString,
			String reimbDescription, Blob reimbReciept, int reimbAuthor, int reimbResolver, int reimbStatusId,
			String reimbStatus, int reimbTypeId, String reimbType) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.reimbSubmittedString = reimbSubmittedString;
		this.reimbResolvedString = reimbResolvedString;
		this.reimbDescription = reimbDescription;
		this.reimbReciept = reimbReciept;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusId = reimbStatusId;
		this.reimbStatus = reimbStatus;
		this.reimbTypeId = reimbTypeId;
		this.reimbType = reimbType;
	}


	public int getReimbId() {
		return reimbId;
	}



	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}



	public BigDecimal getReimbAmount() {
		return reimbAmount;
	}



	public void setReimbAmount(BigDecimal reimbAmount) {
		this.reimbAmount = reimbAmount;
	}



	public Date getReimbSubmitted() {
		return reimbSubmitted;
	}



	public void setReimbSubmitted(Date reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}



	public Date getReimbResolved() {
		return reimbResolved;
	}



	public void setReimbResolved(Date reimbResolved) {
		this.reimbResolved = reimbResolved;
	}



	public String getReimbDescription() {
		return reimbDescription;
	}



	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}



	public Blob getReimbReciept() {
		return reimbReciept;
	}



	public void setReimbReciept(Blob reimbReciept) {
		this.reimbReciept = reimbReciept;
	}



	public int getReimbAuthor() {
		return reimbAuthor;
	}



	public void setReimbAuthor(int reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}



	public int getReimbResolver() {
		return reimbResolver;
	}



	public void setReimbResolver(int reimbResolver) {
		this.reimbResolver = reimbResolver;
	}



	public int getReimbStatusId() {
		return reimbStatusId;
	}



	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}



	public String getReimbStatus() {
		return reimbStatus;
	}



	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}



	public int getReimbTypeId() {
		return reimbTypeId;
	}



	public void setReimbTypeId(int reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}



	public String getReimbType() {
		return reimbType;
	}



	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}



	public String getReimbSubmittedString() {
		return reimbSubmittedString;
	}



	public void setReimbSubmittedString(String reimbSubmittedString) {
		this.reimbSubmittedString = reimbSubmittedString;
	}



	public String getReimbResolvedString() {
		return reimbResolvedString;
	}



	public void setReimbResolvedString(String reimbResolvedString) {
		this.reimbResolvedString = reimbResolvedString;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reimbAmount == null) ? 0 : reimbAmount.hashCode());
		result = prime * result + reimbAuthor;
		result = prime * result + ((reimbDescription == null) ? 0 : reimbDescription.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((reimbResolved == null) ? 0 : reimbResolved.hashCode());
		result = prime * result + reimbResolver;
		result = prime * result + ((reimbStatus == null) ? 0 : reimbStatus.hashCode());
		result = prime * result + reimbStatusId;
		result = prime * result + ((reimbSubmitted == null) ? 0 : reimbSubmitted.hashCode());
		result = prime * result + ((reimbType == null) ? 0 : reimbType.hashCode());
		result = prime * result + reimbTypeId;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbTicket other = (ReimbTicket) obj;
		if (reimbAmount == null) {
			if (other.reimbAmount != null)
				return false;
		} else if (!reimbAmount.equals(other.reimbAmount))
			return false;
		if (reimbAuthor != other.reimbAuthor)
			return false;
		if (reimbDescription == null) {
			if (other.reimbDescription != null)
				return false;
		} else if (!reimbDescription.equals(other.reimbDescription))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (reimbResolved == null) {
			if (other.reimbResolved != null)
				return false;
		} else if (!reimbResolved.equals(other.reimbResolved))
			return false;
		if (reimbResolver != other.reimbResolver)
			return false;
		if (reimbStatus == null) {
			if (other.reimbStatus != null)
				return false;
		} else if (!reimbStatus.equals(other.reimbStatus))
			return false;
		if (reimbStatusId != other.reimbStatusId)
			return false;
		if (reimbSubmitted == null) {
			if (other.reimbSubmitted != null)
				return false;
		} else if (!reimbSubmitted.equals(other.reimbSubmitted))
			return false;
		if (reimbType == null) {
			if (other.reimbType != null)
				return false;
		} else if (!reimbType.equals(other.reimbType))
			return false;
		if (reimbTypeId != other.reimbTypeId)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "ReimbTicket [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", reimbSubmitted=" + reimbSubmitted
				+ ", reimbResolved=" + reimbResolved + ", reimbDescription=" + reimbDescription + ", reimbAuthor="
				+ reimbAuthor + ", reimbResolver=" + reimbResolver + ", reimbStatusId=" + reimbStatusId
				+ ", reimbStatus=" + reimbStatus + ", reimbTypeId=" + reimbTypeId + ", reimbType=" + reimbType + "]";
	}
	

}

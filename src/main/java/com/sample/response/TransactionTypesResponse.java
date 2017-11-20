package com.sample.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionTypesResponse {
	
	private List<Long> transactionIds;

	/**
	 * @return the transactionIds
	 */
	public List<Long> getTransactionIds() {
		return transactionIds;
	}

	/**
	 * @param transactionIds the transactionIds to set
	 */
	public void setTransactionIds(List<Long> transactionIds) {
		this.transactionIds = transactionIds;
	}

	

}

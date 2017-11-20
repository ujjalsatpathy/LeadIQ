package com.sample.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sample.request.TransactionRequest;
import com.sample.response.TransactionResponse;
import com.sample.response.TransactionStatusResponse;
import com.sample.response.TransactionSumResponse;
import com.sample.response.TransactionTypesResponse;

@Component
public class TransactionServiceImpl {
	
	private static final Logger logger = Logger.getLogger(TransactionServiceImpl.class);	
	public static Map<String,List<Long>> typeMap = new HashMap<String, List<Long>>();
	public static Map<Long,Integer> transactionMap = new HashMap<Long, Integer>(); 
	public static Map<Long,TransactionRequest> transactionBoMap = new HashMap<Long, TransactionRequest>();

	/**
	 * method to store data in memory
	 * @param transactionRequest
	 * @param transactionId
	 * @return TransactionStatusResponse
	 */
	public TransactionStatusResponse storeTransactionService(TransactionRequest transactionRequest, long transactionId) {
		logger.info("in storeTransactionService method..");
		TransactionStatusResponse transactionStatusResponse = null;
		try {
			if (null != typeMap.get(transactionRequest.getType())) {
				typeMap.get(transactionRequest.getType()).add(transactionId);

			} else {
				List<Long> transactionIdList = new ArrayList<Long>();
				transactionIdList.add(transactionId);
				typeMap.put(transactionRequest.getType(), transactionIdList);
			}
			
			if(0 != transactionRequest.getParent_id()) {
				
				if (null != transactionMap.get(transactionRequest.getParent_id())) {
					
					int sum = transactionRequest.getAmount() + transactionMap.get(transactionRequest.getParent_id());
					transactionMap.put(transactionRequest.getParent_id(),sum);
					transactionMap.put(transactionId, transactionRequest.getAmount());
				}
				
			} else {
				transactionMap.put(transactionId, transactionRequest.getAmount());
			}
			
			transactionBoMap.put(transactionId, transactionRequest);
			transactionStatusResponse = new TransactionStatusResponse();
			transactionStatusResponse.setStatus("ok");

		} catch (Exception e) {
			logger.error(e.getMessage());
			transactionStatusResponse = new TransactionStatusResponse();
			transactionStatusResponse.setStatus("something went wrong");
		}
		return transactionStatusResponse;
	}

	/**
	 * method to retrive the type data
	 * @param type
	 * @return TransactionTypesResponse
	 */
	public TransactionTypesResponse getTransactionTypeService(String type) {
		logger.info("in getTransactionTypeService method..");
		TransactionTypesResponse transactionTypesResponse = null;
		try{
			transactionTypesResponse = new TransactionTypesResponse();
			List<Long> transactionList = typeMap.get(type);
			transactionTypesResponse.setTransactionIds(transactionList);
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
		return transactionTypesResponse;
	}

	/**
	 * method to get sum of transaction
	 * @param transactionId
	 * @return TransactionSumResponse
	 */
	public TransactionSumResponse getTransactionSumService( long transactionId) {
		logger.info("in getTransactionSumService method..");
		TransactionSumResponse transactionSumResponse = null;
		try{
			transactionSumResponse = new TransactionSumResponse();
			if(null != transactionMap.get(transactionId)) {
				int sum = transactionMap.get(transactionId);
				transactionSumResponse.setSum(sum);
			}
			 
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return transactionSumResponse;
	}

	/**
	 * method to get transaction information
	 * @param transactionId
	 * @return TransactionResponse
	 */
	public TransactionResponse getTransactionResponseService(long transactionId) {
		logger.info("in getTransactionResponseService method..");
		TransactionResponse transactionResponse = null;
		try{
			transactionResponse = new TransactionResponse();
			if(null != transactionBoMap.get(transactionId)) {
				transactionResponse.setAmount(transactionBoMap.get(transactionId).getAmount());
				transactionResponse.setType(transactionBoMap.get(transactionId).getType());
				transactionResponse.setParent_id(transactionBoMap.get(transactionId).getParent_id());
			}			
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		return transactionResponse;
	}

}

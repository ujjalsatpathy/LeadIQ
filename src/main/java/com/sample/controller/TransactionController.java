package com.sample.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.request.TransactionRequest;
import com.sample.response.TransactionResponse;
import com.sample.response.TransactionStatusResponse;
import com.sample.response.TransactionSumResponse;
import com.sample.response.TransactionTypesResponse;
import com.sample.service.TransactionServiceImpl;

@Controller
@Component

public class TransactionController {
	
	private static final Logger logger = Logger.getLogger(TransactionController.class);
	
	@Autowired
	private TransactionServiceImpl transactionServiceImpl;

	/**
	 * method to store the data
	 * @param transactionRequest
	 * @param transactionId
	 * @param request
	 * @param response
	 * @return TransactionStatusResponse
	 */
	@RequestMapping(value = "/transaction/{transactionId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody TransactionStatusResponse putTransaction(@RequestBody TransactionRequest transactionRequest , @PathVariable long transactionId, HttpServletRequest request, HttpServletResponse response) {
	   TransactionStatusResponse transactionResponse = null;
       logger.info("Inside putTransaction controller");
       try{   	   
    	   transactionResponse = new TransactionStatusResponse();   	   
    	   transactionResponse = transactionServiceImpl.storeTransactionService(transactionRequest, transactionId);
       }
       catch(Exception e){
    	   logger.error(e.getMessage());
       }     
       return transactionResponse;
    }
	
	/**
	 * method to get transaction type
	 * @param type
	 * @param request
	 * @param response
	 * @return TransactionTypesResponse
	 */
	@RequestMapping(value = "/types/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody TransactionTypesResponse getTransactionType( @PathVariable String type, HttpServletRequest request, HttpServletResponse response) {
		TransactionTypesResponse transactionTypesResponse = null;
       logger.info("Inside getTransactionType controller");
       try{   	   
    	   transactionTypesResponse = new TransactionTypesResponse();   	   
    	   transactionTypesResponse = transactionServiceImpl.getTransactionTypeService(type);
       }
       catch(Exception e){
    	   logger.error(e.getMessage());
       }     
       return transactionTypesResponse;
    }
	
	/**
	 * method to get sum of transaction
	 * @param transactionId
	 * @param request
	 * @param response
	 * @return TransactionSumResponse
	 */
	@RequestMapping(value = "/sum/{transactionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody TransactionSumResponse getTransactionSum( @PathVariable long transactionId, HttpServletRequest request, HttpServletResponse response) {
		TransactionSumResponse transactionSumResponse = null;
       logger.info("Inside getTransactionSum controller");
       try{   	   
    	   transactionSumResponse = new TransactionSumResponse();   	   
    	   transactionSumResponse = transactionServiceImpl.getTransactionSumService(transactionId);
       }
       catch(Exception e){
    	   logger.error(e.getMessage());
       }     
       return transactionSumResponse;
    }
	
	/**
	 * method to return transaction details
	 * @param transactionId
	 * @param request
	 * @param response
	 * @return TransactionResponse
	 */
	@RequestMapping(value = "/transaction/{transactionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody TransactionResponse getTransactionResponse( @PathVariable long transactionId, HttpServletRequest request, HttpServletResponse response) {
		TransactionResponse transactionResponse = null;
       logger.info("Inside getTransactionResponse controller");
       try{   	   
    	   transactionResponse = new TransactionResponse();   	   
    	   transactionResponse = transactionServiceImpl.getTransactionResponseService(transactionId);
       }
       catch(Exception e){
    	   logger.error(e.getMessage());
       }     
       return transactionResponse;
    }
}

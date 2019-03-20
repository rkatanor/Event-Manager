package com.test.service;

import java.util.List;

import com.test.model.Transaction_History_Event;

public interface TransactionHistoryService {
	public void saveRecord(Transaction_History_Event event);

	public List<Transaction_History_Event> getTransactionHistory(int status_code);
	
	public int updateTHService(int eventid,String status,int status_cd);
}

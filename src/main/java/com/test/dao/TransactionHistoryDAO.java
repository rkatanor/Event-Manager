package com.test.dao;

import java.util.List;

import com.test.model.Transaction_History_Event;

public interface TransactionHistoryDAO {
	public void insertRecord(Transaction_History_Event event);

	public List<Transaction_History_Event> getTransactionHistory(int status_code);
	
	public int updateTransactionHistoryEventStatus(int eventid,String status,int status_cd);
	
}

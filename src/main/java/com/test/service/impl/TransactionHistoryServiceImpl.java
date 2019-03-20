package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.TransactionHistoryDAO;
import com.test.model.Transaction_History_Event;
import com.test.service.TransactionHistoryService;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

	@Autowired
	TransactionHistoryDAO txdao;

	@Override
	public void saveRecord(Transaction_History_Event event) {
		// TODO Auto-generated method stub
		txdao.insertRecord(event);
	}

	@Override
	public List<Transaction_History_Event> getTransactionHistory(int status_code) {
		// TODO Auto-generated method stub

		return txdao.getTransactionHistory(status_code);
	}

	@Override
	public int updateTHService(int eventid, String status, int status_cd) {
		// TODO Auto-generated method stub
		System.out.println("###################" + eventid + ":  " + status + ": " + status_cd);
		return txdao.updateTransactionHistoryEventStatus(eventid, status, status_cd);
	}

}

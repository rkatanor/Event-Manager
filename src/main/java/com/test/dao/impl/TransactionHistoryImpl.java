package com.test.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.test.dao.TransactionHistoryDAO;
import com.test.mappers.Transaction_History_Event_RowMapper;
import com.test.model.Transaction_History_Event;

@Repository
public class TransactionHistoryImpl implements TransactionHistoryDAO {
	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public void insertRecord(Transaction_History_Event event) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO TRANSACTION_HISTORY VALUES (?,?,?,?,?)";
		int update = jdbctemplate.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement arg0) throws SQLException {
				// TODO Auto-generated method stub
				arg0.setInt(1, event.getMember_oid());
				arg0.setString(2, event.getEvent_type());
				arg0.setInt(3, event.getStatus_code());
				arg0.setString(4, event.getStatus());
				arg0.setInt(5, event.getEvent_id());
			}
		});
		if (update == 1) {
			System.out.println("data inserted to th successfully...");
		}
	}

	

	@Override
	public List<Transaction_History_Event> getTransactionHistory(int status_code) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM TRANSACTION_HISTORY WHERE STATUS_CODE=?";
		List<Transaction_History_Event> query = jdbctemplate.query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement arg0) throws SQLException {
				// TODO Auto-generated method stub
				arg0.setInt(1, status_code);
			}
		}, new Transaction_History_Event_RowMapper());
		
		return query;
		
	}

	@Override
	public int updateTransactionHistoryEventStatus(int eventid,String status,int status_cd) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				System.out.println("event OID TO UPADTE TH DB " + eventid);
				String sql = "UPDATE TRANSACTION_HISTORY SET STATUS_CODE=?,STATUS=? WHERE EVENT_ID=?";
				return jdbctemplate.update(sql, new PreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement arg0) throws SQLException {
						// TODO Auto-generated method stub
						arg0.setInt(1, status_cd);
						arg0.setString(2, status);
						arg0.setInt(3, eventid);
					}
				});
	}

}

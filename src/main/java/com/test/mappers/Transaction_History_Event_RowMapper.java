package com.test.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.test.model.Transaction_History_Event;

public class Transaction_History_Event_RowMapper implements RowMapper<Transaction_History_Event> {

	@Override
	public Transaction_History_Event mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Transaction_History_Event event = new Transaction_History_Event();
		event.setMember_oid(rs.getInt(1));
		event.setEvent_type(rs.getString(2));
		event.setStatus_code(rs.getInt(3));
		event.setStatus(rs.getString(4));
		event.setEvent_id(rs.getInt(5));
		return event;
	}

}

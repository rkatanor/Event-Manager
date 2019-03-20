package com.test.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.test.model.Snapshot;

public class SnapshotRowMapper implements RowMapper<Snapshot>{

	@Override
	public Snapshot mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Snapshot snap=new Snapshot();
		snap.setMember_OID(rs.getInt(1));
		snap.setSnapshot(rs.getString(2));
		snap.setLast_change_dt(rs.getTimestamp(3).toString());
		
		return snap;
	}
	

}

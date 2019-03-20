package com.test.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.test.dao.SnapshotDao;
import com.test.mappers.SnapshotRowMapper;
import com.test.model.Snapshot;
import com.test.model.Subscriber;

@Repository
public class SnapshotDaoImpl implements SnapshotDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Snapshot getSnapshotData(Subscriber sub) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM (SELECT SNAPSHOT.* FROM SNAPSHOT WHERE MEMBER_OID=? ORDER BY ROWNUM DESC) WHERE ROWNUM=1; ";
		List<Snapshot> query = jdbcTemplate.query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, sub.getOid());
			}
		}, new SnapshotRowMapper());
		Snapshot snapshot = query.get(0);
		//System.out.println("Retrived Snapshot : " + snapshot);
		return snapshot;
	}

	@Override
	public void storeSnapshotData(Snapshot snap) {
		// TODO Auto-generated method stub
		System.out.println("Inside DAO: " + snap);
		String sql = "INSERT INTO SNAPSHOT VALUES (?,?,?,?)";
		int update = jdbcTemplate.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, snap.getMember_OID());
				ps.setString(2, snap.getSnapshot());
				ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
				ps.setString(4, snap.getSnapshot_type());

			}
		});
		if (update == 1) {
			System.out.println("Data inserted successfully...");
		}
	}

}

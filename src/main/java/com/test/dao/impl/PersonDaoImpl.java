package com.test.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.test.dao.PersonDao;
import com.test.mappers.PersonRowMapper;
import com.test.model.Person;

@Repository
public class PersonDaoImpl implements PersonDao {
	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public void addPerson(Person p) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO PERSON VALUES (?,?,?,?,?,?,?)";
		int update = jdbctemplate.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, p.getPerson_oid());
				ps.setString(2, p.getFirstName());
				ps.setString(3, p.getLastName());
				ps.setString(4, p.getGuid());
				ps.setString(5, p.getSalary());
				ps.setString(6, p.getSsn());
				ps.setString(7, p.getAddress());
			}
		});
		if (update == 1) {
			System.out.println("Person Record inserted...");
		}
	}

	@Override
	public Person retrievePerson(int person_oid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM PERSON P WHERE P.PERSON_OID=?";
		List<Person> query = jdbctemplate.query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement arg0) throws SQLException {
				// TODO Auto-generated method stub
				arg0.setInt(1, person_oid);
			}
		}, new PersonRowMapper());
		return query.get(0);
	}

}

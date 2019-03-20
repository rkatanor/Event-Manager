package com.test.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.test.model.Person;

public class PersonRowMapper implements RowMapper<Person> {
	

	@Override
	public Person mapRow(ResultSet rs, int arg1) throws SQLException {
		Person person=new Person();
		// TODO Auto-generated method stub
		person.setPerson_oid(rs.getInt(1));
		person.setFirstName(rs.getString(2));
		person.setLastName(rs.getString(3));
		person.setGuid(rs.getString(4));
		person.setSalary(rs.getString(5));
		person.setSsn(rs.getString(6));
		person.setAddress(rs.getString(7));
		return person;
	}

}

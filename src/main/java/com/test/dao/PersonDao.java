package com.test.dao;

import com.test.model.Person;

public interface PersonDao {
	public void addPerson(Person p);

	public Person retrievePerson(int person_oid);

}

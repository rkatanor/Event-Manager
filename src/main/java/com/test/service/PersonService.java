package com.test.service;

import com.test.model.Person;

public interface PersonService {
	public void savePerson(Person p);

	public Person getPerson(int person_oid);
	
	
}

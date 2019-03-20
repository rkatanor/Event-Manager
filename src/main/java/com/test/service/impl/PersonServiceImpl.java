package com.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.PersonDao;
import com.test.model.Person;
import com.test.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	PersonDao personDao;

	@Override
	public void savePerson(Person p) {
		// TODO Auto-generated method stub
		personDao.addPerson(p);
	}

	@Override
	public Person getPerson(int person_oid) {
		// TODO Auto-generated method stub
		return personDao.retrievePerson(person_oid);
	}

	
	
}

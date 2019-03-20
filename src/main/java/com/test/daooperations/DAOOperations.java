package com.test.daooperations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.model.Person;
import com.test.service.PersonService;

@Component
public class DAOOperations {
	@Autowired
	PersonService service;

	public Person dataSynchronization(int member_oid) {
			return service.getPerson(member_oid);
			
	}
}

package com.test.controller;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.amqproducer.AMQProducer;
import com.test.daooperations.DAOOperations;
import com.test.model.MemberSnapshot;
import com.test.model.Person;
import com.test.model.Snapshot;
import com.test.model.Transaction_History_Event;
import com.test.processors.EventProcessor;
import com.test.processors.SnapshotDataBuilder;
import com.test.service.PersonService;
import com.test.service.SnapshotService;
import com.test.service.TransactionHistoryService;

@RestController
public class PersonController {
	@Autowired
	SnapshotService snapservice;
	@Autowired
	Snapshot snapshot;
	@Autowired
	DAOOperations operations;
	@Autowired
	AMQProducer producer;
	@Autowired
	PersonService service;
	@Autowired
	Person person;
	@Autowired
	Transaction_History_Event events;
	@Autowired
	SnapshotDataBuilder personBuilder;
	@Autowired
	TransactionHistoryService th_service;
	@Value(value = "${transaction.history.event.status}")
	String EVENT_STATUS;
	@Value(value = "${transaction.history.event.type}")
	String EVENT_TYPE;
	@Value(value="${transaction.history.event.change.type}")
	String CHANGE_EVENT_TYPE;
	@Value(value = "${transaction.history.event.status.code}")
	int EVENT_STATUS_CODE;
	
	@GetMapping(value = "/reseedmember")
	public String syncPerson(@RequestParam("whereid") int memberoid) {
		Person personData = operations.dataSynchronization(memberoid);
		System.out.println("retrived person data from enrollment DB based on given member oid: " + personData);
		System.out.println("updating record in Transaction HIstory for DATASYNC event type");
		events.setEvent_type(EVENT_TYPE);
		events.setMember_oid(personData.getPerson_oid());
		events.setStatus_code(EVENT_STATUS_CODE);
		events.setStatus(EVENT_STATUS);
		events.setEvent_id(new Random().nextInt(12345));
		th_service.saveRecord(events);

		return "Member Reseeded ...";
	}

	@GetMapping(value = "/getperson/{oid}")
	public Person getPerson(@PathVariable("oid") int person_oid) {
		return service.getPerson(person_oid);
	}

	@GetMapping(value = "/createmember")
	public String addMember(@RequestParam("wheressn") String SSN) {	
		MemberSnapshot process = personBuilder.process(SSN);
		snapshot.setSnapshot(process.toString());
		snapshot.setMember_OID(process.getSubscriber().get(0).getOid());
		snapshot.setLast_change_dt("");
		snapshot.setSnapshot_type("Transaction");
		snapservice.saveSnapshot(snapshot);
		System.out.println(snapshot);

		return "Member Successfully added into the system";

	}

	@GetMapping(value="/updatemember")
	public String changeMemberData(@RequestParam("whereid") int memberid) {
		Person personData = operations.dataSynchronization(memberid);
		System.out.println("retrived person data from enrollment DB based on given member oid: " + personData);
		System.out.println("updating record in Transaction HIstory for DATASYNC event type");
		events.setEvent_type(CHANGE_EVENT_TYPE);
		events.setMember_oid(personData.getPerson_oid());
		events.setStatus_code(EVENT_STATUS_CODE);
		events.setStatus(EVENT_STATUS);
		events.setEvent_id(new Random().nextInt(12345));
		th_service.saveRecord(events);
		return "Member Data Updated!!";
	}
	
	@GetMapping(value = "/poll")
	public void polldb() {

		try {
			producer.processStatus();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

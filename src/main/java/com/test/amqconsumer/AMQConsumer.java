package com.test.amqconsumer;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.test.model.MemberSnapshot;
import com.test.model.Person;
import com.test.model.Snapshot;
import com.test.processors.EventProcessor;
import com.test.service.PersonService;
import com.test.service.SnapshotService;
import com.test.service.TransactionHistoryService;

@Component
public class AMQConsumer {
	@Autowired
	PersonService service;
	@Autowired
	TransactionHistoryService thservice;
	@Autowired
	Snapshot snapshot;
	@Autowired
	SnapshotService Snapshotservice;
	@Value(value = "${transaction.history.snapshot.type.synchronization}")
	String SNAPSHOT_TYPE;
	
	private int eventid;

	@JmsListener(destination = "DATASYNC_EVENT_QUEUE")
	public void DataSynEventConsumer(final TextMessage message) throws JMSException {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String eventID = message.getText();
		
		System.out.println("consumed member eventID " + eventID);
		System.out.println("member oid : " + message.getIntProperty("memberoid"));
		
		int personoid=message.getIntProperty("memberoid");
		
		
		System.out.println("Message DEQUEUED ");
		
		Person personData = pulldatafromPersonTableBasedonOID(personoid);
		System.out.println("retrived person data from person table :"+personData);
		EventProcessor processor = new EventProcessor();
		MemberSnapshot membersnapshot = processor.process(personData);
		snapshot.setSnapshot(membersnapshot.toString());
		snapshot.setMember_OID(membersnapshot.getSubscriber().get(0).getOid());
		snapshot.setSnapshot_type(SNAPSHOT_TYPE);
		snapshot.setLast_change_dt("03-19-2019");

		Snapshotservice.saveSnapshot(snapshot);
		
		System.out.println("new snapshot saved: "+snapshot);
		int updateTransactionHistory = updateTransactionHistory(Integer.parseInt(eventID),"PROCESSED",2500);
		if (updateTransactionHistory == 1) {
			System.out.println("updated th history");
		}

	}

	
	// CHANGE EVENT QUEUE

	@JmsListener(destination = "CHANGE_EVENT_QUEUE")
	public void ChangeEventConsumer(final TextMessage message) throws JMSException {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String eventID = message.getText();
		
		System.out.println("consumed member eventID " + eventID);
		System.out.println("member oid : " + message.getIntProperty("memberoid"));
		
		int personoid=message.getIntProperty("memberoid");
		
		
		System.out.println("Message DEQUEUED ");
		
		Person personData = pulldatafromPersonTableBasedonOID(personoid);
		System.out.println("retrived person data from person table :"+personData);
		EventProcessor processor = new EventProcessor();
		MemberSnapshot membersnapshot = processor.process(personData);
		snapshot.setSnapshot(membersnapshot.toString());
		snapshot.setMember_OID(membersnapshot.getSubscriber().get(0).getOid());
		snapshot.setSnapshot_type("TRANSACTION");
		snapshot.setLast_change_dt("03-19-2019");

		Snapshotservice.saveSnapshot(snapshot);
		
		System.out.println("new snapshot saved: "+snapshot);
		int updateTransactionHistory = updateTransactionHistory(Integer.parseInt(eventID),"PROCESSED",2500);
		if (updateTransactionHistory == 1) {
			System.out.println("updated th history");
		}

	}

	
	private int updateTransactionHistory(int person_oid,String status,int status_cd) {
		return thservice.updateTHService(person_oid,status,status_cd);

	}

	public Person pulldatafromPersonTableBasedonOID(int oid) {
		return service.getPerson(oid);
	}
}

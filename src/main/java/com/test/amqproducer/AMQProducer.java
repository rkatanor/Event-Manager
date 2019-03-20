package com.test.amqproducer;

import java.util.List;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.test.model.Transaction_History_Event;
import com.test.service.TransactionHistoryService;

@Component
public class AMQProducer {
	ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

	// Create a Connection

	Transaction_History_Event events;
	@Autowired
	TransactionHistoryService th_service;
	@Value(value = "${transaction.history.event.status.code}")
	int status_code;

	public void processStatus() throws JMSException {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TransactionHistoryPoller();
		}
	}

	public void TransactionHistoryPoller() throws JMSException {
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// Create a Session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// Create the destination (Topic or Queue)
		Destination destination1 = session.createQueue("DATASYNC_EVENT_QUEUE");
		Destination destination2 = session.createQueue("CHANGE_EVENT_QUEUE");

		// Create a MessageProducer from the Session to the Topic or Queue
		MessageProducer producer1 = session.createProducer(destination1);
		MessageProducer producer2 = session.createProducer(destination2);
		producer1.setDeliveryMode(DeliveryMode.PERSISTENT);
		producer2.setDeliveryMode(DeliveryMode.PERSISTENT);
		
		List<Transaction_History_Event> transactionHistory = th_service.getTransactionHistory(status_code);

		for(Transaction_History_Event th : transactionHistory) {
			if(th.getEvent_type().equals("DATASYNC_EVENT")) {
			System.out.println("Sending EVENT ID: " + th.getEvent_id() + "to AMQ");
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int event_id = th.getEvent_id();
			String event_ID = event_id + "";
			TextMessage message = session.createTextMessage(event_ID);
			message.setIntProperty("memberoid", th.getMember_oid());
			producer1.send(message);
			System.out.println("event sent to AMQ ...");
			updateTHStatusFromPENDINGtoQUEUED(th.getEvent_id());
		}else if(th.getEvent_type().equals("CHANGE_EVENT")) {
			System.out.println("Sending EVENT ID: " + th.getEvent_id() + "to AMQ");
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int event_id = th.getEvent_id();
			String event_ID = event_id + "";
			TextMessage message = session.createTextMessage(event_ID);
			message.setIntProperty("memberoid", th.getMember_oid());
			producer2.send(message);
			System.out.println("event sent to AMQ ...");
			updateTHStatusFromPENDINGtoQUEUED(th.getEvent_id());

		}
		}
		connection.close();
	}

	private void updateTHStatusFromPENDINGtoQUEUED(int eventID) {
		// TODO Auto-generated method stub
		int updateTHStatus = th_service.updateTHService(eventID,"QUEUED",2000);
		if (updateTHStatus == 1) {
			System.out.println("updated status from PENDING TO QUEUED for EVENT-ID: " + eventID);
		} else {
			System.out.println("status still PENDING...");
		}
	}

}

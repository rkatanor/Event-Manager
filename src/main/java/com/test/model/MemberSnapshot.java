package com.test.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@Component
public class MemberSnapshot {
	@JacksonXmlElementWrapper(useWrapping= false)
	private List<Subscriber> subscriber;

	public List<Subscriber> getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(List<Subscriber> subscriber) {
		this.subscriber = subscriber;
	}

	@Override
	public String toString() {
		return "Snapshot [subscriber=" + subscriber + "]";
	}
	
}

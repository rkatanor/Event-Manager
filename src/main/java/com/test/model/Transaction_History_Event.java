package com.test.model;

import org.springframework.stereotype.Component;

@Component
public class Transaction_History_Event {
	private int member_oid;
	private String event_type;
	private int status_code;
	private String status;
	private int event_id;

	public int getMember_oid() {
		return member_oid;
	}

	public void setMember_oid(int member_oid) {
		this.member_oid = member_oid;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public int getStatus_code() {
		return status_code;
	}

	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	@Override
	public String toString() {
		return "Transaction_History_Event [member_oid=" + member_oid + ", event_type=" + event_type + ", status_code="
				+ status_code + ", status=" + status + ", event_id=" + event_id + "]";
	}

}

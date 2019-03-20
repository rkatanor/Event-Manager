package com.test.model;

import org.springframework.stereotype.Component;

@Component
public class Snapshot {
	private int member_OID;
	private String snapshot;
	private String last_change_dt;
	private String snapshot_type;

	public int getMember_OID() {
		return member_OID;
	}

	public void setMember_OID(int member_OID) {
		this.member_OID = member_OID;
	}

	public String getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}

	public String getLast_change_dt() {
		return last_change_dt;
	}

	public void setLast_change_dt(String last_change_dt) {
		this.last_change_dt = last_change_dt;
	}

	public String getSnapshot_type() {
		return snapshot_type;
	}

	public void setSnapshot_type(String snapshot_type) {
		this.snapshot_type = snapshot_type;
	}

	@Override
	public String toString() {
		return "Snapshot [member_OID=" + member_OID + ", snapshot=" + snapshot + ", last_change_dt=" + last_change_dt
				+ ", snapshot_type=" + snapshot_type + "]";
	}

}

package com.test.model;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class MemberShipEnrollment {
	private int member_oid;
	private int enrollment_oid;
	private String benefit_type;
	private int accepted_indicator;
	private String last_changed_by;
	private Timestamp last_changed_dt;

	public int getMember_oid() {
		return member_oid;
	}

	public void setMember_oid(int member_oid) {
		this.member_oid = member_oid;
	}

	public int getEnrollment_oid() {
		return enrollment_oid;
	}

	public void setEnrollment_oid(int enrollment_oid) {
		this.enrollment_oid = enrollment_oid;
	}

	public String getBenefit_type() {
		return benefit_type;
	}

	public void setBenefit_type(String benefit_type) {
		this.benefit_type = benefit_type;
	}

	public int getAccepted_indicator() {
		return accepted_indicator;
	}

	public void setAccepted_indicator(int accepted_indicator) {
		this.accepted_indicator = accepted_indicator;
	}

	public String getLast_changed_by() {
		return last_changed_by;
	}

	public void setLast_changed_by(String last_changed_by) {
		this.last_changed_by = last_changed_by;
	}

	public Timestamp getLast_changed_dt() {
		return last_changed_dt;
	}

	public void setLast_changed_dt(Timestamp last_changed_dt) {
		this.last_changed_dt = last_changed_dt;
	}

	@Override
	public String toString() {
		return "MemberShipEnrollment [member_oid=" + member_oid + ", enrollment_oid=" + enrollment_oid
				+ ", benefit_type=" + benefit_type + ", accepted_indicator=" + accepted_indicator + ", last_changed_by="
				+ last_changed_by + ", last_changed_dt=" + last_changed_dt + "]";
	}

}

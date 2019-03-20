package com.test.model;

public class EnrollmentRate {
	private int enrollment_oid;
	private String plan_name;
	private String plan_cost;

	public int getEnrollment_oid() {
		return enrollment_oid;
	}

	public void setEnrollment_oid(int enrollment_oid) {
		this.enrollment_oid = enrollment_oid;
	}

	public String getPlan_name() {
		return plan_name;
	}

	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}

	public String getPlan_cost() {
		return plan_cost;
	}

	public void setPlan_cost(String plan_cost) {
		this.plan_cost = plan_cost;
	}

	@Override
	public String toString() {
		return "EnrollmentRate [enrollment_oid=" + enrollment_oid + ", plan_name=" + plan_name + ", plan_cost="
				+ plan_cost + "]";
	}

}

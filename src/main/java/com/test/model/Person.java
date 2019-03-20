package com.test.model;

import org.springframework.stereotype.Component;

@Component
public class Person {
	private int person_oid;
	private String firstName;
	private String lastName;
	private String guid;
	private String salary;
	private String ssn;
	private String address;

	public int getPerson_oid() {
		return person_oid;
	}

	public void setPerson_oid(int person_oid) {
		this.person_oid = person_oid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [person_oid=" + person_oid + ", firstName=" + firstName + ", lastName=" + lastName + ", guid="
				+ guid + ", salary=" + salary + ", ssn=" + ssn + ", address=" + address + "]";
	}

}

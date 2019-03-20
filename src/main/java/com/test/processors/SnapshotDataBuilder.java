package com.test.processors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.daooperations.DAOOperations;
import com.test.model.Benefits;
import com.test.model.MemberSnapshot;
import com.test.model.Person;
import com.test.model.Snapshot;
import com.test.model.Subscriber;
import com.test.service.PersonService;
import com.test.utility.PojoToXml;
@Component
public class SnapshotDataBuilder {
	@Autowired
	MemberSnapshot memsnap;
	@Autowired
	Snapshot snap;
	@Autowired
	PersonService personService;
	@Autowired
	Person p;
	@Autowired
	DAOOperations operations;
	public MemberSnapshot process(String ssn) {
	
		p.setSsn(ssn);
		p.setAddress("MIYAPUR");
		p.setFirstName("siva");
		p.setLastName("nulakani");
		p.setSalary("$23000");
		p.setGuid("ATUVXXL1234455");
		p.setPerson_oid(1266788622);
		personService.savePerson(p);
		MemberSnapshot member_snap = new MemberSnapshot();

		Benefits benefit1 = new Benefits();
		benefit1.setBenefit_name("Medical");
		benefit1.setBenefit_rate("$11");

		Benefits benefit2 = new Benefits();
		benefit2.setBenefit_name("Dental");
		benefit2.setBenefit_rate("$63");

		Benefits benefit3 = new Benefits();
		benefit3.setBenefit_name("Vision");
		benefit3.setBenefit_rate("$89");

		List<Benefits> b = new ArrayList<>();
		b.add(benefit1);
		b.add(benefit2);
		b.add(benefit3);

		Subscriber sub = new Subscriber();
		sub.setOid(p.getPerson_oid());
		sub.setAddress(p.getAddress());
		sub.setBenefits(b);
		sub.setFirst_name(p.getFirstName());
		sub.setGuid(p.getGuid());
		sub.setLast_name(p.getLastName());
		sub.setSalary(p.getSalary());
		sub.setSSN(p.getSsn());

		List<com.test.model.Subscriber> listSubscriber = new ArrayList<>();
		listSubscriber.add(sub);
		member_snap.setSubscriber(listSubscriber);
		MemberSnapshot memberData=null;
		try {
			memberData= PojoToXml.convert(member_snap);
			// System.out.println(readValue);
			//System.out.println(data);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberData;
	}
	
	public MemberSnapshot changeData(int memberoid) {
		Person personData = operations.dataSynchronization(memberoid);
		p.setSsn(personData.getSsn());
		p.setAddress(personData.getAddress());
		p.setFirstName(personData.getFirstName());
		p.setLastName(personData.getLastName());
		p.setSalary(personData.getSalary());
		p.setGuid(personData.getGuid());
		p.setPerson_oid(memberoid);
		personService.savePerson(p);
		MemberSnapshot member_snap = new MemberSnapshot();

		Benefits benefit1 = new Benefits();
		benefit1.setBenefit_name("Medical");
		benefit1.setBenefit_rate("$120");

		Benefits benefit2 = new Benefits();
		benefit2.setBenefit_name("Dental");
		benefit2.setBenefit_rate("$63");

		Benefits benefit3 = new Benefits();
		benefit3.setBenefit_name("Vision");
		benefit3.setBenefit_rate("$89");

		List<Benefits> b = new ArrayList<>();
		b.add(benefit1);
		b.add(benefit2);
		b.add(benefit3);

		Subscriber sub = new Subscriber();
		sub.setOid(p.getPerson_oid());
		sub.setAddress(p.getAddress());
		sub.setBenefits(b);
		sub.setFirst_name(p.getFirstName());
		sub.setGuid(p.getGuid());
		sub.setLast_name(p.getLastName());
		sub.setSalary(p.getSalary());
		sub.setSSN(p.getSsn());

		List<com.test.model.Subscriber> listSubscriber = new ArrayList<>();
		listSubscriber.add(sub);
		member_snap.setSubscriber(listSubscriber);
		MemberSnapshot memberData=null;
		try {
			memberData= PojoToXml.convert(member_snap);
			// System.out.println(readValue);
			//System.out.println(data);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberData;
	}


}

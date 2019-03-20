package com.test.processors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.test.model.Benefits;
import com.test.model.MemberSnapshot;
import com.test.model.Person;
import com.test.model.Snapshot;
import com.test.model.Subscriber;
import com.test.utility.PojoToXml;

public class EventProcessor {

	@Autowired
	MemberSnapshot memsnap;
	@Autowired
	Snapshot snap;

	public MemberSnapshot process(Person person) {

		MemberSnapshot member_snap = new MemberSnapshot();

		Benefits benefit1 = new Benefits();
		benefit1.setBenefit_name("Medical");
		benefit1.setBenefit_rate("$140");

		Benefits benefit2 = new Benefits();
		benefit2.setBenefit_name("Dental");
		benefit2.setBenefit_rate("$60");

		Benefits benefit3 = new Benefits();
		benefit3.setBenefit_name("Vision");
		benefit3.setBenefit_rate("$90");

		List<Benefits> b = new ArrayList<>();
		b.add(benefit1);
		b.add(benefit2);
		b.add(benefit3);
       
		Subscriber sub = new Subscriber();
		sub.setOid(person.getPerson_oid());
		sub.setAddress(person.getAddress());
		sub.setBenefits(b);
		sub.setFirst_name(person.getFirstName());
		sub.setGuid(person.getGuid());
		sub.setLast_name(person.getLastName());
		sub.setSalary(person.getSalary());
		sub.setSSN(person.getSsn());

		List<com.test.model.Subscriber> listSubscriber = new ArrayList<>();
		listSubscriber.add(sub);
		member_snap.setSubscriber(listSubscriber);
		MemberSnapshot memberData = null;
		try {
			memberData = PojoToXml.convert(member_snap);
			///System.out.println(readValue);
			System.out.println(memberData);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberData;
	}

}

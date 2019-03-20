package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.MemberSnapshot;
import com.test.model.Snapshot;
import com.test.model.Subscriber;
import com.test.processors.SnapshotDataBuilder;
import com.test.service.SnapshotService;

@RestController
public class SnapshotController {
	@Autowired
	SnapshotDataBuilder databuilder;
	@Autowired
	SnapshotService service;
	@Autowired
	Snapshot snapshot;

	@GetMapping(value = "/insertSnapshot")
	public String insertSnapshot() {
/*
		MemberSnapshot memberData = databuilder.process();
		snapshot.setSnapshot(memberData.toString());
		snapshot.setMember_OID(memberData.getSubscriber().get(0).getOid());
		snapshot.setLast_change_dt("03-19-2019");

		service.saveSnapshot(snapshot);
		System.out.println(snapshot);*/
		return "see console for more data";
	}

	@GetMapping(value = "/getSnapshot/{oid}")
	public Snapshot getSnapshot(@PathVariable("oid") int memberoid) {
		Subscriber sub = new Subscriber();
		sub.setOid(memberoid);
		Snapshot snapshot2 = service.getSnapshot(sub);
		System.out.println("Retrived snapshot data from DB :" + snapshot2);
		return snapshot2;

	}

}

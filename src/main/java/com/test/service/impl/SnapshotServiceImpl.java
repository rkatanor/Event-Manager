package com.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.SnapshotDao;
import com.test.model.Snapshot;
import com.test.model.Subscriber;
import com.test.service.SnapshotService;
@Service
public class SnapshotServiceImpl implements SnapshotService{
	@Autowired
     SnapshotDao snapshotdao;
	
	@Override
	public Snapshot getSnapshot(Subscriber sub) {
		// TODO Auto-generated method stub
		return snapshotdao.getSnapshotData(sub);
	}

	@Override
	public void saveSnapshot(Snapshot snap) {
		  System.out.println("Inside SERVICE :" +snap);
		  snapshotdao.storeSnapshotData(snap);
		 
		
	}

}

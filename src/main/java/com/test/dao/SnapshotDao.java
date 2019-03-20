package com.test.dao;

import com.test.model.Snapshot;
import com.test.model.Subscriber;

public interface SnapshotDao {
	public Snapshot getSnapshotData(Subscriber sub);

	public void storeSnapshotData(Snapshot snap);
	
}

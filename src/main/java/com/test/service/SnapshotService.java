package com.test.service;

import com.test.model.Snapshot;
import com.test.model.Subscriber;

public interface SnapshotService {
		public Snapshot getSnapshot(Subscriber sub);
		public void saveSnapshot(Snapshot snap);
}

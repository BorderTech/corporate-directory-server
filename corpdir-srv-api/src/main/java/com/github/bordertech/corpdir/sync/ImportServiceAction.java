package com.github.bordertech.corpdir.sync;

import com.github.bordertech.didums.Didums;
import com.github.bordertech.taskmaster.service.ServiceAction;

/**
 *
 * @author exiqaj
 * @param <S>
 * @param <T>
 */
public class ImportServiceAction<S, T> implements ServiceAction<S, T> {
	
	enum SyncEntities {
		UNITLEVEL (UnitLevelSynchronisation.class),
		POSITIONTYPE (PositionTypeSynchronisation.class),
		LOCATION (LocationSynchronisation.class);
		
		final Class<? extends DataSynchronisation> syncClazz;
		
		SyncEntities (final Class<? extends DataSynchronisation> clazz) {
			this.syncClazz = clazz;
		}

		Class<? extends DataSynchronisation> getSyncClazz() {
			return syncClazz;
		}
		
		/**
		 * Create sync class instance
		 * @return sync instance
		 */
		DataSynchronisation createSyncClazzInstance() {
			DataSynchronisation service = Didums.getService(getSyncClazz());
			return service;
		}
	}
	
	enum SyncVersionEntities {
		ORGUNIT (OrgUnitSynchronisation.class),
		POSITION (PositionSynchronisation.class),
		CONTACT (ContactSynchronisation.class);
		
		final Class<? extends DataVersionSynchronisation> syncClazz;
	
		SyncVersionEntities (final Class<? extends DataVersionSynchronisation> clazz) {
			this.syncClazz = clazz;
		}

		Class<? extends DataVersionSynchronisation> getSyncClazz() {
			return syncClazz;
		}
		
		/**
		 * Create sync class instance
		 * @return sync instance
		 */
		DataVersionSynchronisation createSyncClazzInstance() {
			DataVersionSynchronisation service = Didums.getService(getSyncClazz());
			return service;
		}
	}

	@Override
	public T service(S criteria) {
		
		
		for (SyncEntities syncEntity : SyncEntities.values()) {
			DataSynchronisation syncInstance = syncEntity.createSyncClazzInstance();
			syncInstance.syncBaseData();
		}
		
		// Sync all base data, base data needs to exist ahead of linking assosiated data
		for (SyncVersionEntities syncEntity : SyncVersionEntities.values()) {
			DataVersionSynchronisation syncInstance = syncEntity.createSyncClazzInstance();
			syncInstance.syncBaseData();
		}
		// Sync linked data
//		for (SyncVersionEntities syncEntity : SyncVersionEntities.values()) {
//			DataVersionSynchronisation syncInstance = syncEntity.createSyncClazzInstance();
//			syncInstance.syncLinkedData();
//		}
		
		return null;
	}
	
}

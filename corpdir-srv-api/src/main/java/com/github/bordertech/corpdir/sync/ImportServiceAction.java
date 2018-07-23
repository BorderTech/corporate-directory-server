package com.github.bordertech.corpdir.sync;

import com.github.bordertech.didums.Didums;
import com.github.bordertech.taskmaster.service.ServiceAction;

/**
 *
 * @author exiqaj
 */
public class ImportServiceAction implements ServiceAction<String, String> {

    /**
     * API key object stand-alone entities.
     */
    enum KeyIdEntities {
	UNITLEVEL(UnitLevelSynchronisation.class),
	POSITIONTYPE(PositionTypeSynchronisation.class),
	LOCATION(LocationSynchronisation.class);

	final Class<? extends DataSynchronisation> syncClazz;

	KeyIdEntities(final Class<? extends DataSynchronisation> clazz) {
	    this.syncClazz = clazz;
	}

	Class<? extends DataSynchronisation> getSyncClazz() {
	    return syncClazz;
	}

	/**
	 * Create sync class instance
	 *
	 * @return sync instance
	 */
	DataSynchronisation createSyncClazzInstance() {
	    DataSynchronisation service = Didums.getService(getSyncClazz());
	    return service;
	}
    }

    /**
     * API key object linked entities with version data.
     */
    enum KeyIdVersionEntities {
	ORGUNIT(OrgUnitSynchronisation.class),
	POSITION(PositionSynchronisation.class),
	CONTACT(ContactSynchronisation.class);

	final Class<? extends DataVersionSynchronisation> syncClazz;

	KeyIdVersionEntities(final Class<? extends DataVersionSynchronisation> clazz) {
	    this.syncClazz = clazz;
	}

	Class<? extends DataVersionSynchronisation> getSyncClazz() {
	    return syncClazz;
	}

	/**
	 * Create sync class instance
	 *
	 * @return sync instance
	 */
	DataVersionSynchronisation createSyncClazzInstance() {
	    DataVersionSynchronisation service = Didums.getService(getSyncClazz());
	    return service;
	}
    }

    @Override
    public String service(String criteria) {
	try {
	    // API entities base data
	    for (KeyIdEntities syncEntity : KeyIdEntities.values()) {
		DataSynchronisation syncInstance = syncEntity.createSyncClazzInstance();
		syncInstance.syncBaseData();
	    }

	    // API version entities base data, base data needs to exist ahead of linking assosiated data
	    for (KeyIdVersionEntities syncEntity : KeyIdVersionEntities.values()) {
		DataVersionSynchronisation syncInstance = syncEntity.createSyncClazzInstance();
		syncInstance.syncBaseData();
	    }
	    // TODO: return version that was created/updated?
	    return null;
	} catch (Exception e) {
	    // TODO: return exception message?
	    throw e;
	}
    }

}

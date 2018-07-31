package com.github.bordertech.corpdir.sync.service;

import com.github.bordertech.corpdir.api.common.ApiIdObject;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.VersionCtrlService;
import com.github.bordertech.corpdir.api.v1.model.VersionCtrl;
import com.github.bordertech.corpdir.sync.common.DataSynchronisation;
import com.github.bordertech.corpdir.sync.common.DataVersionSynchronisation;
import com.github.bordertech.corpdir.sync.impl.ContactSynchronisation;
import com.github.bordertech.corpdir.sync.impl.LocationSynchronisation;
import com.github.bordertech.corpdir.sync.impl.OrgUnitSynchronisation;
import com.github.bordertech.corpdir.sync.impl.PositionSynchronisation;
import com.github.bordertech.corpdir.sync.impl.PositionTypeSynchronisation;
import com.github.bordertech.corpdir.sync.impl.UnitLevelSynchronisation;
import com.github.bordertech.didums.Didums;
import com.github.bordertech.taskmaster.service.ServiceAction;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 *
 * @author aswinkandula
 */
public class ImportServiceAction implements ServiceAction<String, ApiIdObject> {

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
	public ApiIdObject service(String criteria) {
		try {
			// API entities base data
			for (KeyIdEntities syncEntity : KeyIdEntities.values()) {
				DataSynchronisation syncInstance = syncEntity.createSyncClazzInstance();
				syncInstance.syncBaseData();
			}
	
			// Get or create version
			VersionCtrl todayVersion = getOrCreateNewVersion();
			String versionStr;
			if (todayVersion.getId().startsWith(ApiIdObject.ID_PREFIX)) {
				versionStr = todayVersion.getId().substring(1);
			} else {
				versionStr = todayVersion.getId();
			}
			// API version entities base data, base data needs to exist ahead of linking assosiated data
			final Long versionId = Long.parseLong(versionStr);
			for (KeyIdVersionEntities syncEntity : KeyIdVersionEntities.values()) {
				DataVersionSynchronisation syncInstance = syncEntity.createSyncClazzInstance();
				syncInstance.syncBaseData(versionId);
			}
			return todayVersion;
		} catch (Exception e) {
			// TODO: return exception message?
			throw e;
		}
	}

	private VersionCtrl getOrCreateNewVersion() {
		// TODO: should version creation be here? or injected by a view?
		final String today = DateFormatUtils.ISO_DATE_FORMAT.format(new Date());
		final VersionCtrlService versionService = Didums.getService(VersionCtrlService.class);
		DataResponse<List<VersionCtrl>> result = versionService.search(today);
		VersionCtrl todayVersion;
		if (result.getData().isEmpty()) {
			VersionCtrl newVersion = new VersionCtrl(null);
			newVersion.setDescription(today);
			versionService.create(newVersion);
			result = versionService.search(today);
			todayVersion = result.getData().get(0);
		} else {
			todayVersion = result.getData().get(0);
		}
		return todayVersion;
	}

}

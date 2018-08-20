package com.github.bordertech.corpdir.sync.service;

import com.github.bordertech.corpdir.api.common.ApiIdObject;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.VersionCtrlService;
import com.github.bordertech.corpdir.api.v1.model.VersionCtrl;
import com.github.bordertech.corpdir.sync.apis.ContactSynchronisation;
import com.github.bordertech.corpdir.sync.apis.LocationSynchronisation;
import com.github.bordertech.corpdir.sync.apis.OrgUnitSynchronisation;
import com.github.bordertech.corpdir.sync.apis.PositionSynchronisation;
import com.github.bordertech.corpdir.sync.apis.PositionTypeSynchronisation;
import com.github.bordertech.corpdir.sync.apis.UnitTypeSynchronisation;
import com.github.bordertech.corpdir.sync.common.ApiKeyIdSynchronisation;
import com.github.bordertech.corpdir.sync.common.ApiVersionKeyIdSynchronisation;
import com.github.bordertech.corpdir.sync.service.exception.SynchronisationException;
import com.github.bordertech.didums.Didums;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * Sync service implementation.
 *
 * @author aswinkandula
 * @since 1.0.0
 */

public class SynchronisationServiceImpl implements SynchronisationService<ApiIdObject> {

	/**
	 * API key object stand-alone entities.
	 */
	enum ApiKeyIdEntities {
		UNITLEVEL(UnitTypeSynchronisation.class),
		POSITIONTYPE(PositionTypeSynchronisation.class),
		LOCATION(LocationSynchronisation.class);

		final Class<? extends ApiKeyIdSynchronisation> syncClazz;

		ApiKeyIdEntities(final Class<? extends ApiKeyIdSynchronisation> clazz) {
			this.syncClazz = clazz;
		}

		Class<? extends ApiKeyIdSynchronisation> getSyncClazz() {
			return syncClazz;
		}

		/**
		 * Create sync class instance
		 *
		 * @return sync instance
		 */
		ApiKeyIdSynchronisation createSyncClazzInstance() {
			ApiKeyIdSynchronisation service = Didums.getService(getSyncClazz());
			return service;
		}
	}

	/**
	 * API key object linked entities with version data.
	 */
	enum ApiKeyIdVersionEntities {
		ORGUNIT(OrgUnitSynchronisation.class),
		POSITION(PositionSynchronisation.class),
		CONTACT(ContactSynchronisation.class);

		final Class<? extends ApiVersionKeyIdSynchronisation> syncClazz;

		ApiKeyIdVersionEntities(final Class<? extends ApiVersionKeyIdSynchronisation> clazz) {
			this.syncClazz = clazz;
		}

		Class<? extends ApiVersionKeyIdSynchronisation> getSyncClazz() {
			return syncClazz;
		}

		/**
		 * Create sync class instance
		 *
		 * @return sync instance
		 */
		ApiVersionKeyIdSynchronisation createSyncClazzInstance() {
			ApiVersionKeyIdSynchronisation service = Didums.getService(getSyncClazz());
			return service;
		}
	}

	@Override
	public ApiIdObject sync() {
		try {
			// API entities base data
			for (ApiKeyIdEntities syncEntity : ApiKeyIdEntities.values()) {
				ApiKeyIdSynchronisation syncInstance = syncEntity.createSyncClazzInstance();
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
			for (ApiKeyIdVersionEntities syncEntity : ApiKeyIdVersionEntities.values()) {
				ApiVersionKeyIdSynchronisation syncInstance = syncEntity.createSyncClazzInstance();
				syncInstance.syncBaseData(versionId);
			}
			return todayVersion;
		} catch (Exception e) {
			throw new SynchronisationException("Error while performing sync", e);
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

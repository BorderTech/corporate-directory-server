package com.github.bordertech.corpdir.sync;

import com.github.bordertech.corpdir.api.common.ApiIdObject;
import com.github.bordertech.corpdir.api.common.ApiKeyIdObject;
import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.exception.NotFoundException;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicKeyIdReadOnlyService;
import com.github.bordertech.corpdir.api.service.BasicKeyIdService;
import com.github.bordertech.corpdir.api.service.BasicVersionKeyIdReadOnlyService;
import com.github.bordertech.corpdir.api.service.BasicVersionKeyIdService;
import com.github.bordertech.corpdir.api.v1.VersionCtrlService;
import com.github.bordertech.corpdir.api.v1.model.VersionCtrl;
import com.github.bordertech.didums.Didums;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * One-way version synchronisation from Source to Destination
 * 
 * @author exiqaj
 * @param <S> Read-only Service Api
 * @param <D> Read-Write Service Api
 * @param <A> Data Api
 */
public abstract class AbstractVersionSynchronisation <S extends BasicKeyIdReadOnlyService & BasicVersionKeyIdReadOnlyService, D extends BasicKeyIdService & BasicVersionKeyIdService, A extends ApiKeyIdObject & ApiVersionable> 
		extends AbstractSynchronisation<S, D, A>
		implements DataVersionSynchronisation<S, D, A> {
	
	private final String today = DateFormatUtils.ISO_DATE_FORMAT.format(new Date());
	
	private final VersionCtrlService versionService;
	
	protected AbstractVersionSynchronisation(S sourceService, D destinationService) {
		super (sourceService, destinationService);
		versionService = Didums.getService(VersionCtrlService.class);
	}

	@Override
	public String getOrCreateNewVersion() {
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
		if (todayVersion.getId().startsWith(ApiIdObject.ID_PREFIX)) {
			return todayVersion.getId().substring(1);
		} else {
			return todayVersion.getId();
		}
	}
	
	@Override
	public void createOrUpdateData(Long versionId, A fromApiData) {
		DataResponse<A> retrievedEntity;
		try {
			retrievedEntity = getDestinationService().retrieve(versionId, fromApiData.getBusinessKey());
		} catch (NotFoundException e) {
			retrievedEntity = new DataResponse<>(null);
		}
		if (retrievedEntity.getData() == null) {
			fromApiData.setVersionId(versionId);
			getDestinationService().create(fromApiData);
		} else {
			fromApiData.setVersionId(retrievedEntity.getData().getVersionId());
			
			setId(fromApiData, retrievedEntity);
			
			getDestinationService().update(fromApiData.getBusinessKey(), fromApiData);
		}
	}
}

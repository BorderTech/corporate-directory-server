package com.github.bordertech.corpdir.sync.common;

import com.github.bordertech.corpdir.api.common.ApiKeyIdObject;
import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.exception.NotFoundException;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicKeyIdReadOnlyService;
import com.github.bordertech.corpdir.api.service.BasicKeyIdService;
import com.github.bordertech.corpdir.api.service.BasicVersionKeyIdReadOnlyService;
import com.github.bordertech.corpdir.api.service.BasicVersionKeyIdService;
import java.util.List;

/**
 * One-way version synchronisation from Source to Destination
 *
 * @param <S> Read-only Service Api
 * @param <D> Read-Write Service Api
 * @param <A> the keyed API version object
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public abstract class AbstractVersionSynchronisation<S extends BasicKeyIdReadOnlyService & BasicVersionKeyIdReadOnlyService, D extends BasicKeyIdService & BasicVersionKeyIdService, A extends ApiKeyIdObject & ApiVersionable>
	extends AbstractSynchronisation<S, D, A>
	implements DataVersionSynchronisation<S, D, A> {

	protected AbstractVersionSynchronisation(S sourceService, D destinationService) {
		super(sourceService, destinationService);
	}

	@Override
	public void syncBaseData(final Long versionId) {
		DataResponse<List<A>> sourceEntities = getSourceData();
		for (A sourceEntity : sourceEntities.getData()) {
			createOrUpdateData(versionId, sourceEntity);
		}
	}

	@Override
	public void syncLinkedData(final Long version) {
		// TODO
	}

	private void createOrUpdateData(Long versionId, A fromApiData) {
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

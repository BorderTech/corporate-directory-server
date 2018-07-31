package com.github.bordertech.corpdir.sync.common;

import com.github.bordertech.corpdir.api.common.ApiKeyIdObject;
import com.github.bordertech.corpdir.api.exception.NotFoundException;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicKeyIdReadOnlyService;
import com.github.bordertech.corpdir.api.service.BasicKeyIdService;
import java.util.List;
import org.apache.commons.lang.reflect.FieldUtils;

/**
 * One-way synchronisation from Source to Destination
 *
 * @param <S> Read-only Service Api
 * @param <D> Read-Write Service Api
 * @param <A> the keyed API version object
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public abstract class AbstractSynchronisation<S extends BasicKeyIdReadOnlyService, D extends BasicKeyIdService, A extends ApiKeyIdObject> implements DataSynchronisation<S, D, A> {

	private final S sourceService;

	private final D destinationService;

	protected AbstractSynchronisation(S sourceService, D destinationService) {
		this.sourceService = sourceService;
		this.destinationService = destinationService;
	}

	@Override
	public DataResponse<List<A>> getSourceData() {
		return getSourceService().search(null);
	}

	@Override
	public void syncBaseData() {
		DataResponse<List<A>> sourceEntities = getSourceData();
		for (A sourceEntity : sourceEntities.getData()) {
			createOrUpdateData(sourceEntity);
		}
	}

	private void createOrUpdateData(A fromApiData) {
		DataResponse<A> retrievedEntity;
		try {
			retrievedEntity = getDestinationService().retrieve(fromApiData.getBusinessKey());
		} catch (NotFoundException e) {
			retrievedEntity = new DataResponse<>(null);
		}
		if (retrievedEntity.getData() == null) {
			getDestinationService().create(fromApiData);
		} else {
			setId(fromApiData, retrievedEntity);
			getDestinationService().update(fromApiData.getBusinessKey(), fromApiData);
		}
	}

	protected void setId(A fromApiData, DataResponse<A> retrievedEntity) {
		try {
			// TODO can ApiIdObject.setId() be made public?
			FieldUtils.writeField(fromApiData, "id", retrievedEntity.getData().getId(), true);
		} catch (IllegalAccessException ex) {
			// TODO do what?
		}
	}

	@Override
	public S getSourceService() {
		return sourceService;
	}

	@Override
	public D getDestinationService() {
		return destinationService;
	}
}

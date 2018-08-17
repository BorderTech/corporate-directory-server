package com.github.bordertech.corpdir.sync.common;

import com.github.bordertech.corpdir.api.common.ApiKeyIdObject;
import com.github.bordertech.corpdir.api.exception.NotFoundException;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicKeyIdReadOnlyService;
import com.github.bordertech.corpdir.api.service.BasicKeyIdService;
import java.util.List;

/**
 * Abstract one-way synchronisation from Source to Destination
 *
 * @param <A> the keyed API object
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public abstract class AbstractKeyIdSynchronisation<A extends ApiKeyIdObject> 
	implements ApiKeyIdSynchronisation<A> {

	private final BasicKeyIdReadOnlyService<A> sourceService;

	private final BasicKeyIdService<A> destinationService;

	protected AbstractKeyIdSynchronisation(final BasicKeyIdReadOnlyService<A> sourceService, 
						final BasicKeyIdService<A> destinationService) {
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
		sourceEntities.getData().forEach((A sourceEntity) -> {
			createOrUpdateData(sourceEntity);
		});
	}

	@Override
	public void createOrUpdateData(final A fromApiData) {
		DataResponse<A> retrievedEntity;
		try {
			retrievedEntity = getDestinationService().retrieve(fromApiData.getBusinessKey());
		} catch (NotFoundException e) {
			retrievedEntity = new DataResponse<>(null);
		}
		if (retrievedEntity.getData() == null) {
			getDestinationService().create(fromApiData);
		} else {
			A toDataApi = retrievedEntity.getData();
			getApiMapper().map(fromApiData, toDataApi);
			getDestinationService().update(toDataApi.getBusinessKey(), toDataApi);
		}
	}

	@Override
	public BasicKeyIdReadOnlyService<A> getSourceService() {
		return sourceService;
	}

	@Override
	public BasicKeyIdService<A> getDestinationService() {
		return destinationService;
	}
}

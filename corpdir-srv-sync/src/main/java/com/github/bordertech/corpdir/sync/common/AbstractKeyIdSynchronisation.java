package com.github.bordertech.corpdir.sync.common;

import com.github.bordertech.corpdir.api.common.ApiKeyIdObject;
import com.github.bordertech.corpdir.api.exception.NotFoundException;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicKeyIdReadOnlyService;
import com.github.bordertech.corpdir.api.service.BasicKeyIdService;
import com.github.bordertech.corpdir.sync.api.mapper.ApiModelMapper;
import java.util.List;

/**
 * Abstract one-way synchronisation from Source to Destination
 *
 * @param <S> Read-only Service Api to fetch data
 * @param <D> Read-Write Service Api to save data
 * @param <A> the keyed API object
 * @param <M> mapper to copy API data
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public abstract class AbstractKeyIdSynchronisation<A extends ApiKeyIdObject,
						S extends BasicKeyIdReadOnlyService<A>, 
						D extends BasicKeyIdService<A>, 
						M extends ApiModelMapper<A>> 
	implements ApiKeyIdSynchronisation<A, S, D, M> {

	private final S sourceService;

	private final D destinationService;

	protected AbstractKeyIdSynchronisation(S sourceService, D destinationService) {
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

	@Override
	public void createOrUpdateData(A fromApiData) {
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
	public S getSourceService() {
		return sourceService;
	}

	@Override
	public D getDestinationService() {
		return destinationService;
	}
}

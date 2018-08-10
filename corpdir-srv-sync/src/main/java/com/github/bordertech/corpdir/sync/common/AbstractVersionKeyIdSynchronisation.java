package com.github.bordertech.corpdir.sync.common;

import com.github.bordertech.corpdir.api.common.ApiKeyIdObject;
import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicKeyIdReadOnlyService;
import com.github.bordertech.corpdir.api.service.BasicKeyIdService;
import com.github.bordertech.corpdir.api.service.BasicVersionKeyIdReadOnlyService;
import com.github.bordertech.corpdir.api.service.BasicVersionKeyIdService;
import com.github.bordertech.corpdir.sync.api.mapper.ApiModelMapper;
import java.util.List;

/**
 * Abstract one-way version synchronisation from Source to Destination
 *
 * @param <S> Read-only Service Api to fetch data
 * @param <D> Read-Write Service Api to save data
 * @param <A> the keyed API version object
 * @param <M> mapper to copy API data
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public abstract class AbstractVersionKeyIdSynchronisation<A extends ApiKeyIdObject & ApiVersionable,
							S extends BasicKeyIdReadOnlyService<A> & BasicVersionKeyIdReadOnlyService<A>, 
							D extends BasicKeyIdService<A> & BasicVersionKeyIdService<A>, 
							M extends ApiModelMapper<A>> 
	extends AbstractKeyIdSynchronisation<A, S, D, M>
	implements ApiVersionKeyIdSynchronisation<A, S, D, M> {

	protected AbstractVersionKeyIdSynchronisation(S sourceService, D destinationService) {
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

	@Override
	public void createOrUpdateData(Long versionId, A fromApiData) {
		fromApiData.setVersionId(versionId);
		createOrUpdateData(fromApiData);
	}
}

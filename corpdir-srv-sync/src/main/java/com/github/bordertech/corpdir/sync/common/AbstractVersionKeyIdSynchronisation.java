package com.github.bordertech.corpdir.sync.common;

import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicVersionKeyIdReadOnlyService;
import com.github.bordertech.corpdir.api.service.BasicVersionKeyIdService;
import java.util.List;

/**
 * Abstract one-way version synchronisation from Source to Destination
 *
 * @param <A> the keyed API version object
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public abstract class AbstractVersionKeyIdSynchronisation<A extends ApiVersionable> 
	extends AbstractKeyIdSynchronisation<A>
	implements ApiVersionKeyIdSynchronisation<A> {

	protected AbstractVersionKeyIdSynchronisation(final BasicVersionKeyIdReadOnlyService<A> sourceService, 
							final BasicVersionKeyIdService<A> destinationService) {
		super(sourceService, destinationService);
	}

	@Override
	public void syncBaseData(final Long versionId) {
		DataResponse<List<A>> sourceEntities = getSourceData();
		sourceEntities.getData().forEach((A sourceEntity) -> {
			createOrUpdateData(versionId, sourceEntity);
		});
	}

	@Override
	public void syncLinkedData(final Long version) {
		// TODO
	}

	@Override
	public void createOrUpdateData(final Long versionId, final A fromApiData) {
		fromApiData.setVersionId(versionId);
		createOrUpdateData(fromApiData);
	}

	@Override
	public BasicVersionKeyIdReadOnlyService<A> getSourceService() {
		return (BasicVersionKeyIdReadOnlyService<A>) super.getSourceService();
	}

	@Override
	public BasicVersionKeyIdService<A> getDestinationService() {
		return (BasicVersionKeyIdService<A>) super.getDestinationService();
	}

}

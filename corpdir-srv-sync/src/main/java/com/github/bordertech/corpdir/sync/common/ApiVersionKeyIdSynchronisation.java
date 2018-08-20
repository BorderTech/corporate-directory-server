package com.github.bordertech.corpdir.sync.common;

import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.service.BasicVersionKeyIdReadOnlyService;
import com.github.bordertech.corpdir.api.service.BasicVersionKeyIdService;

/**
 * Synchronisation service for keyed API object with version data.
 * 
 * @param <A> the keyed API version object
 *
 * @author aswinkandula
 * @since 1.0.0
 */
public interface ApiVersionKeyIdSynchronisation<A extends ApiVersionable> 
	extends ApiKeyIdSynchronisation<A> {

	@Override
	BasicVersionKeyIdReadOnlyService<A> getSourceService();
	
	@Override
	BasicVersionKeyIdService<A> getDestinationService();
	
	void syncBaseData(final Long version);

	void syncLinkedData(final Long version);

	void createOrUpdateData(final Long version, final A fromApiData);
}

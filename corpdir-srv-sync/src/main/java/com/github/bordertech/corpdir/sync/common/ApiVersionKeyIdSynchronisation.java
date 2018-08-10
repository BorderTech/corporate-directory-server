package com.github.bordertech.corpdir.sync.common;

import com.github.bordertech.corpdir.api.common.ApiKeyIdObject;
import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.service.BasicKeyIdReadOnlyService;
import com.github.bordertech.corpdir.api.service.BasicKeyIdService;
import com.github.bordertech.corpdir.api.service.BasicVersionKeyIdReadOnlyService;
import com.github.bordertech.corpdir.api.service.BasicVersionKeyIdService;
import com.github.bordertech.corpdir.sync.api.mapper.ApiModelMapper;

/**
 * Synchronisation service for keyed API object with version data.
 * 
 * @param <S> Read-only Service Api to fetch data
 * @param <D> Read-Write Service Api to save data
 * @param <A> the keyed API version object
 * @param <M> mapper to copy API data
 *
 * @author aswinkandula
 * @since 1.0.0
 */
public interface ApiVersionKeyIdSynchronisation<A extends ApiKeyIdObject & ApiVersionable,
						S extends BasicKeyIdReadOnlyService<A> & BasicVersionKeyIdReadOnlyService<A>, 
						D extends BasicKeyIdService<A> & BasicVersionKeyIdService<A>, 
						M extends ApiModelMapper<A>> 
	extends ApiKeyIdSynchronisation<A, S, D, M> {

	void syncBaseData(final Long version);

	void syncLinkedData(final Long version);

	void createOrUpdateData(Long version, A fromApiData);
}

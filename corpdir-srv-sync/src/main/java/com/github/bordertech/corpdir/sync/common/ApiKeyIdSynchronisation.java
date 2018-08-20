package com.github.bordertech.corpdir.sync.common;

import com.github.bordertech.corpdir.api.common.ApiKeyIdObject;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicKeyIdReadOnlyService;
import com.github.bordertech.corpdir.api.service.BasicKeyIdService;
import com.github.bordertech.corpdir.sync.api.mapper.ApiModelMapper;
import java.util.List;

/**
 * Synchronisation service for keyed API object.
 * 
 * @param <A> the keyed API object
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public interface ApiKeyIdSynchronisation<A extends ApiKeyIdObject> {

	BasicKeyIdReadOnlyService<A> getSourceService();

	BasicKeyIdService<A> getDestinationService();

	void syncBaseData();

	DataResponse<List<A>> getSourceData();
	
	void createOrUpdateData(final A fromApiData);
	
	ApiModelMapper<A> getApiMapper();

}

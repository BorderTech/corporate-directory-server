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
 * @param <S> Read-only Service Api to fetch data
 * @param <D> Read-Write Service Api to save data
 * @param <A> the keyed API object
 * @param <M> mapper to copy API data
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public interface ApiKeyIdSynchronisation<A extends ApiKeyIdObject,
					S extends BasicKeyIdReadOnlyService<A>, 
					D extends BasicKeyIdService<A>, 
					M extends ApiModelMapper<A>> {

	S getSourceService();

	D getDestinationService();

	void syncBaseData();

	DataResponse<List<A>> getSourceData();
	
	void createOrUpdateData(A fromApiData);
	
	M getApiMapper();

}

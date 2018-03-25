package com.github.bordertech.corpdir.api.service;

import com.github.bordertech.corpdir.api.common.ApiIdObject;
import com.github.bordertech.corpdir.api.response.BasicResponse;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.modify.BasicIdWriteService;
import com.github.bordertech.corpdir.api.service.readonly.BasicIdReadOnlyService;
import java.io.Serializable;
import java.util.List;

/**
 * Basic service for ID API object.
 *
 * @param <T> the keyed API object
 * @author Jonathan Austin
 * @since 1.0.0
 * @deprecated split in read and write
 */
@Deprecated
public interface BasicIdService<T extends ApiIdObject> extends Serializable {

	/**
	 *
	 * @param search
	 * @return 
	 * @deprecated use {@link BasicIdReadOnlyService#search(java.lang.String) } instead.
	 */
	@Deprecated
	DataResponse<List<T>> search(final String search);

	/**
	 *
	 * @param id
	 * @return 
	 * @deprecated use {@link BasicIdReadOnlyService#retrieve(java.lang.String) } instead.
	 */
	@Deprecated
	DataResponse<T> retrieve(final String id);

	/**
	 *
	 * @param apiObject
	 * @return 
	 * @deprecated use {@link BasicIdWriteService#create(com.github.bordertech.corpdir.api.common.ApiIdObject) } instead.
	 */
	@Deprecated
	DataResponse<T> create(final T apiObject);

	/**
	 *
	 * @param id
	 * @param apiObject
	 * @return 
	 * @deprecated use {@link BasicIdWriteService#update(java.lang.String, com.github.bordertech.corpdir.api.common.ApiIdObject) } instead.
	 */
	@Deprecated
	DataResponse<T> update(final String id, final T apiObject);

	/**
	 *
	 * @param id
	 * @return 
	 * @deprecated use {@link BasicIdWriteService#delete(java.lang.String) } instead.
	 */
	@Deprecated
	BasicResponse delete(final String id);

}

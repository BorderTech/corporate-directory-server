package com.github.bordertech.corpdir.api.service;

import com.github.bordertech.corpdir.api.common.ApiIdObject;
import com.github.bordertech.corpdir.api.response.BasicResponse;
import com.github.bordertech.corpdir.api.response.DataResponse;
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
	 * @deprecated use {@link BasicIdReadOnlyService#search()} instead.
	 */
	@Deprecated
	DataResponse<List<T>> search(final String search);

	/**
	 *
	 * @param id
	 * @return 
	 * @deprecated use {@link BasicIdReadOnlyService#retrieve()} instead.
	 */
	@Deprecated
	DataResponse<T> retrieve(final String id);

	/**
	 *
	 * @param apiObject
	 * @return 
	 * @deprecated use {@link BasicIdWriteService#create()} instead.
	 */
	@Deprecated
	DataResponse<T> create(final T apiObject);

	/**
	 *
	 * @param id
	 * @param apiObject
	 * @return 
	 * @deprecated use {@link BasicIdWriteService#update()} instead.
	 */
	@Deprecated
	DataResponse<T> update(final String id, final T apiObject);

	/**
	 *
	 * @param id
	 * @return 
	 * @deprecated use {@link BasicIdWriteService#delete()} instead.
	 */
	@Deprecated
	BasicResponse delete(final String id);

}

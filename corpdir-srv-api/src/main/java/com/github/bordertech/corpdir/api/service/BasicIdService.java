package com.github.bordertech.corpdir.api.service;

import com.github.bordertech.corpdir.api.common.ApiIdObject;
import com.github.bordertech.corpdir.api.response.BasicResponse;
import com.github.bordertech.corpdir.api.response.DataResponse;

/**
 * Basic read and write service for ID API object.
 *
 * @param <T> the keyed API object
 * @author Jonathan Austin
 * 
 * @since 1.0.0
 */
public interface BasicIdService<T extends ApiIdObject> extends BasicIdReadOnlyService<T> {

	DataResponse<T> create(final T apiObject);

	DataResponse<T> update(final String id, final T apiObject);

	BasicResponse delete(final String id);
}

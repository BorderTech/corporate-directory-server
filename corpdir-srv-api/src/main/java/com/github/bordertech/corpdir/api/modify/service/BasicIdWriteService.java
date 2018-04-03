package com.github.bordertech.corpdir.api.modify.service;

import com.github.bordertech.corpdir.api.common.ApiIdObject;
import com.github.bordertech.corpdir.api.response.BasicResponse;
import com.github.bordertech.corpdir.api.response.DataResponse;
import java.io.Serializable;

/**
 * Basic write (modifiable) service for ID API object.
 *
 * @param <T> the keyed API object
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface BasicIdWriteService<T extends ApiIdObject> extends Serializable {

	DataResponse<T> create(final T apiObject);

	DataResponse<T> update(final String id, final T apiObject);

	BasicResponse delete(final String id);
}

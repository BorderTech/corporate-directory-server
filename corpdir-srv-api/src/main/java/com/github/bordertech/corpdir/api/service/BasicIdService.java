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
 * @deprecated split in {@link BasicIdReadOnlyService} and {@link BasicIdWriteService}
 */
@Deprecated
public interface BasicIdService<T extends ApiIdObject> extends Serializable {

	@Deprecated
	DataResponse<List<T>> search(final String search);

	@Deprecated
	DataResponse<T> retrieve(final String id);

	@Deprecated
	DataResponse<T> create(final T apiObject);

	@Deprecated
	DataResponse<T> update(final String id, final T apiObject);

	@Deprecated
	BasicResponse delete(final String id);

}

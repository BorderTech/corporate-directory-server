package com.github.bordertech.corpdir.api.service.modify;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;

/**
 * Basic write service for Tree API Object with a version.
 *
 * @param <T> the tree API object
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface BasicVersionTreeWriteService<T extends ApiTreeable & ApiVersionable> extends BasicVersionKeyIdWriteService<T>, BasicTreeWriteService<T> {
    
	DataResponse<T> addSub(final Long versionId, final String keyId, final String subKeyId);

	DataResponse<T> removeSub(final Long versionId, final String keyId, final String subKeyId);
}

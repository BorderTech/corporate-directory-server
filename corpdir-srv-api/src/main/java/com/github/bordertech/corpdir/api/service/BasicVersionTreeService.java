package com.github.bordertech.corpdir.api.service;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;

/**
 * Basic read and write service for Tree API Object with a version.
 *
 * @param <T> the tree API object
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface BasicVersionTreeService<T extends ApiTreeable & ApiVersionable> extends BasicVersionTreeReadOnlyService<T>, BasicVersionKeyIdService<T>, BasicTreeService<T> {
    
	DataResponse<T> addSub(final Long versionId, final String keyId, final String subKeyId);

	DataResponse<T> removeSub(final Long versionId, final String keyId, final String subKeyId);
}

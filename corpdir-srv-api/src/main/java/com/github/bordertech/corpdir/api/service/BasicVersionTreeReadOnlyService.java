package com.github.bordertech.corpdir.api.readonly.service;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import java.util.List;

/**
 * Basic read-only service for Tree API Object with a version.
 *
 * @param <T> the tree API object
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface BasicVersionTreeReadOnlyService<T extends ApiTreeable & ApiVersionable> extends BasicVersionKeyIdReadOnlyService<T>, BasicTreeReadOnlyService<T> {

	DataResponse<List<T>> getSubs(final Long versionId, final String keyId);

	DataResponse<List<T>> getRootItems(final Long versionId);
}

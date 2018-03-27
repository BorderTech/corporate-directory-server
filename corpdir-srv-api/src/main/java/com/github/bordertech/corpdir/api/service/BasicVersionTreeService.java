package com.github.bordertech.corpdir.api.service;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.modify.BasicVersionTreeWriteService;
import com.github.bordertech.corpdir.api.service.readonly.BasicVersionTreeReadOnlyService;
import java.util.List;

/**
 * Basic service for Tree API Object with a version.
 *
 * @param <T> the tree API object
 * @author Jonathan Austin
 * @since 1.0.0
 * @deprecated split in {@link BasicVersionTreeReadOnlyService} and {@link BasicVersionTreeWriteService}
 */
@Deprecated
public interface BasicVersionTreeService<T extends ApiTreeable & ApiVersionable> extends BasicVersionKeyIdService<T>, BasicTreeService<T> {

	@Deprecated
	DataResponse<List<T>> getSubs(final Long versionId, final String keyId);

	@Deprecated
	DataResponse<T> addSub(final Long versionId, final String keyId, final String subKeyId);


	@Deprecated
	DataResponse<T> removeSub(final Long versionId, final String keyId, final String subKeyId);

	@Deprecated
	DataResponse<List<T>> getRootItems(final Long versionId);

}

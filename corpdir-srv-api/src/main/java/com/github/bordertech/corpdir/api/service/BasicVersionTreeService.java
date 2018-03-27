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

	/**
	 *
	 * @param versionId
	 * @param keyId
	 * @return 
	 * @deprecated use {@link BasicVersionTreeReadOnlyService#getSubs(java.lang.Long, java.lang.String) } instead.  
	 */
	@Deprecated
	DataResponse<List<T>> getSubs(final Long versionId, final String keyId);

	/**
	 *
	 * @param versionId
	 * @param keyId
	 * @param subKeyId
	 * @return 
	 * @deprecated use {@link BasicVersionTreeWriteService#addSub(java.lang.Long, java.lang.String, java.lang.String) } instead.  
	 */
	@Deprecated
	DataResponse<T> addSub(final Long versionId, final String keyId, final String subKeyId);


	/**
	 *
	 * @param versionId
	 * @param keyId
	 * @param subKeyId
	 * @return 
	 * @deprecated use {@link BasicVersionTreeWriteService#removeSub(java.lang.Long, java.lang.String, java.lang.String) } instead.  
	 */
	@Deprecated
	DataResponse<T> removeSub(final Long versionId, final String keyId, final String subKeyId);

	/**
	 *
	 * @param versionId
	 * @return 
	 * @deprecated use {@link BasicVersionTreeReadOnlyService#getRootItems(java.lang.Long) } instead.  
	 */
	@Deprecated
	DataResponse<List<T>> getRootItems(final Long versionId);

}

package com.github.bordertech.corpdir.api.service;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import java.util.List;

/**
 * Basic service for Tree API Object with a version.
 *
 * @param <T> the tree API object
 * @author Jonathan Austin
 * @since 1.0.0
 * @deprecated split in read and write
 */
@Deprecated
public interface BasicVersionTreeService<T extends ApiTreeable & ApiVersionable> extends BasicVersionKeyIdService<T>, BasicTreeService<T> {

	/**
	 *
	 * @param versionId
	 * @param keyId
	 * @return 
	 * @deprecated use {@link BasicVersionTreeReadOnlyService#getSubs()} instead.  
	 */
	@Deprecated
	DataResponse<List<T>> getSubs(final Long versionId, final String keyId);

	/**
	 *
	 * @param versionId
	 * @param keyId
	 * @param subKeyId
	 * @return 
	 * @deprecated use {@link BasicVersionTreeWriteService#addSub()} instead.  
	 */
	@Deprecated
	DataResponse<T> addSub(final Long versionId, final String keyId, final String subKeyId);


	/**
	 *
	 * @param versionId
	 * @param keyId
	 * @param subKeyId
	 * @return 
	 * @deprecated use {@link BasicVersionTreeWriteService#removeSub()} instead.  
	 */
	@Deprecated
	DataResponse<T> removeSub(final Long versionId, final String keyId, final String subKeyId);

	/**
	 *
	 * @param versionId
	 * @return 
	 * @deprecated use {@link BasicVersionTreeReadOnlyService#getRootItems()} instead.  
	 */
	@Deprecated
	DataResponse<List<T>> getRootItems(final Long versionId);

}

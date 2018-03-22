package com.github.bordertech.corpdir.api.service;

import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import java.util.List;

/**
 * Basic service for keyed API object that have version data.
 *
 * @param <T> the keyed API object
 * @author Jonathan Austin
 * @since 1.0.0
 * @deprecated split in read and write
 */
@Deprecated
public interface BasicVersionKeyIdService<T extends ApiVersionable> extends BasicKeyIdService<T> {

	/**
	 *
	 * @param versionId
	 * @param search
	 * @return 
	 * @deprecated use {@link BasicVersionKeyIdReadOnlyService#search()} instead.  
	 */
	@Deprecated
	DataResponse<List<T>> search(final Long versionId, final String search);

	/**
	 *
	 * @param versionId
	 * @param keyId
	 * @return 
	 * @deprecated use {@link BasicVersionKeyIdReadOnlyService#retrieve()} instead.  
	 */
	@Deprecated
	DataResponse<T> retrieve(final Long versionId, final String keyId);

}

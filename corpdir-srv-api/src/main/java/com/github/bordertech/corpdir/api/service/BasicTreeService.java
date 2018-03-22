package com.github.bordertech.corpdir.api.service;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import java.util.List;

/**
 * Basic service for Tree API object.
 *
 * @param <T> the tree API object
 * @author Jonathan Austin
 * @since 1.0.0
 * @deprecated split in read and write
 */
@Deprecated
public interface BasicTreeService<T extends ApiTreeable> extends BasicKeyIdService<T> {

	/**
	 *
	 * @param keyId
	 * @return 
	 * @deprecated use {@link BasicTreeReadOnlyService#getSubs()} instead.  
	 */
	@Deprecated
	DataResponse<List<T>> getSubs(final String keyId);

	/**
	 *
	 * @param keyId
	 * @param subKeyId
	 * @return 
	 * @deprecated use {@link BasicTreeWriteService#addSub()} instead.  
	 */
	@Deprecated
	DataResponse<T> addSub(final String keyId, final String subKeyId);

	/**
	 *
	 * @param keyId
	 * @param subKeyId
	 * @return 
	 * @deprecated use {@link BasicTreeWriteService#removeSub()} instead.  
	 */
	@Deprecated
	DataResponse<T> removeSub(final String keyId, final String subKeyId);

	/**
	 *
	 * @return 
	 * @deprecated use {@link BasicTreeReadOnlyService#getRootItems()} instead.  
	 */
	@Deprecated
	DataResponse<List<T>> getRootItems();

}

package com.github.bordertech.corpdir.api.service;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.modify.BasicTreeWriteService;
import com.github.bordertech.corpdir.api.service.readonly.BasicTreeReadOnlyService;
import java.util.List;

/**
 * Basic service for Tree API object.
 *
 * @param <T> the tree API object
 * @author Jonathan Austin
 * @since 1.0.0
 * @deprecated split in {@link BasicTreeReadOnlyService} and {@link BasicTreeWriteService}
 */
@Deprecated
public interface BasicTreeService<T extends ApiTreeable> extends BasicKeyIdService<T> {

	@Deprecated
	DataResponse<List<T>> getSubs(final String keyId);

	@Deprecated
	DataResponse<T> addSub(final String keyId, final String subKeyId);

	@Deprecated
	DataResponse<T> removeSub(final String keyId, final String subKeyId);

	@Deprecated
	DataResponse<List<T>> getRootItems();

}

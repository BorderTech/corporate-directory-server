package com.github.bordertech.corpdir.api.service;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.response.DataResponse;

/**
 * Basic read and write service for Tree API object.
 *
 * @param <T> the tree API object
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface BasicTreeService<T extends ApiTreeable> extends BasicTreeReadOnlyService<T>, BasicKeyIdService<T> {

	DataResponse<T> addSub(final String keyId, final String subKeyId);

	DataResponse<T> removeSub(final String keyId, final String subKeyId);
}

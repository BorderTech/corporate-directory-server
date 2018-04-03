package com.github.bordertech.corpdir.api.modify.service;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.response.DataResponse;

/**
 * Basic write (modifiable) service for Tree API object.
 *
 * @param <T> the tree API object
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface BasicTreeWriteService<T extends ApiTreeable> extends BasicKeyIdWriteService<T> {

	DataResponse<T> addSub(final String keyId, final String subKeyId);

	DataResponse<T> removeSub(final String keyId, final String subKeyId);
}

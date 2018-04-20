package com.github.bordertech.corpdir.api.readonly.service;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import java.util.List;

/**
 * Basic read-only service for Tree API object.
 *
 * @param <T> the tree API object
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface BasicTreeReadOnlyService<T extends ApiTreeable> extends BasicKeyIdReadOnlyService<T> {

	DataResponse<List<T>> getSubs(final String keyId);

	DataResponse<List<T>> getRootItems();
}

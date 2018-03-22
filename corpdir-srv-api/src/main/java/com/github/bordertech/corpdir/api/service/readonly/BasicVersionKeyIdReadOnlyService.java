package com.github.bordertech.corpdir.api.service.readonly;

import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import java.util.List;

/**
 * Basic read-only service for keyed API object that have version data.
 *
 * @param <T> the keyed API object
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface BasicVersionKeyIdReadOnlyService<T extends ApiVersionable> extends BasicKeyIdReadOnlyService<T> {

	DataResponse<List<T>> search(final Long versionId, final String search);

	DataResponse<T> retrieve(final Long versionId, final String keyId);
}

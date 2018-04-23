package com.github.bordertech.corpdir.api.v1.func;

import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;

/**
 * Position read and write functions.
 *
 * @author Jonathan Austin
 * @param <T> the type having positions added to it
 * @since 1.0.0
 */
public interface PositionFunctions<T extends ApiVersionable> extends PositionReadOnlyFunctions<T> {

	DataResponse<T> addPosition(final String keyId, final String positionKeyId);

	DataResponse<T> removePosition(final String keyId, final String positionKeyId);

	DataResponse<T> addPosition(final Long versionId, final String keyId, final String positionKeyId);

	DataResponse<T> removePosition(final Long versionId, final String keyId, final String positionKeyId);
}

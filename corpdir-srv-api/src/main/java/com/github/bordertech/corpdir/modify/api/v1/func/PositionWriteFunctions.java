package com.github.bordertech.corpdir.modify.api.v1.func;

import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import java.io.Serializable;

/**
 * Position write (modifiable) functions.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @param <T> the type having positions added to it
 * @since 1.0.0
 */
public interface PositionWriteFunctions<T extends ApiVersionable> extends Serializable {

	DataResponse<T> addPosition(final String keyId, final String positionKeyId);

	DataResponse<T> removePosition(final String keyId, final String positionKeyId);

	DataResponse<T> addPosition(final Long versionId, final String keyId, final String positionKeyId);

	DataResponse<T> removePosition(final Long versionId, final String keyId, final String positionKeyId);
}

package com.github.bordertech.corpdir.readonly.api.v1.func;

import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.Position;
import java.io.Serializable;
import java.util.List;

/**
 * Position read-only functions.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @param <T> the type having positions added to it
 * @since 1.0.0
 */
public interface PositionReadOnlyFunctions<T extends ApiVersionable> extends Serializable {

	DataResponse<List<Position>> getPositions(final String keyId);

	DataResponse<List<Position>> getPositions(final Long versionId, final String keyId);
}

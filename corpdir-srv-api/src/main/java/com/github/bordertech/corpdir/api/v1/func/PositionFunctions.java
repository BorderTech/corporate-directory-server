package com.github.bordertech.corpdir.api.v1.func;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.Position;
import java.io.Serializable;
import java.util.List;

/**
 * Position functions.
 *
 * @author Jonathan Austin
 * @param <T> the type being maintained
 * @since 1.0.0
 */
public interface PositionFunctions<T> extends Serializable {

	DataResponse<List<Position>> getPositions(final String keyId);

	DataResponse<T> addPosition(final String keyId, final String positionKeyId);

	DataResponse<T> removePosition(final String keyId, final String positionKeyId);

	DataResponse<List<Position>> getPositions(final Integer versionId, final String keyId);

	DataResponse<T> addPosition(final Integer versionId, final String keyId, final String positionKeyId);

	DataResponse<T> removePosition(final Integer versionId, final String keyId, final String positionKeyId);

}

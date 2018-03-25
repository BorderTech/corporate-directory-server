package com.github.bordertech.corpdir.api.v1.func;

import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.modify.api.v1.func.PositionWriteFunctions;
import com.github.bordertech.corpdir.readonly.api.v1.func.PositionReadOnlyFunctions;
import java.io.Serializable;
import java.util.List;

/**
 * Position functions.
 *
 * @author Jonathan Austin
 * @param <T> the type having positions added to it
 * @since 1.0.0
 * @deprecated split in read and write
 */
@Deprecated
public interface PositionFunctions<T extends ApiVersionable> extends Serializable {

	/**
	 *
	 * @param keyId
	 * @return 
	 * @deprecated use {@link PositionReadOnlyFunctions#getPositions(java.lang.String) } instead.
	 */
	@Deprecated
	DataResponse<List<Position>> getPositions(final String keyId);

	/**
	 *
	 * @param keyId
	 * @param positionKeyId
	 * @return 
	 * @deprecated use {@link PositionWriteFunctions#addPosition(java.lang.String, java.lang.String) } instead.
	 */
	@Deprecated
	DataResponse<T> addPosition(final String keyId, final String positionKeyId);

	/**
	 *
	 * @param keyId
	 * @param positionKeyId
	 * @return 
	 * @deprecated use {@link PositionWriteFunctions#removePosition(java.lang.String, java.lang.String) } instead.
	 */
	@Deprecated
	DataResponse<T> removePosition(final String keyId, final String positionKeyId);

	/**
	 *
	 * @param versionId
	 * @param keyId
	 * @return 
	 * @deprecated use {@link PositionReadOnlyFunctions#getPositions(java.lang.Long, java.lang.String) } instead.
	 */
	@Deprecated
	DataResponse<List<Position>> getPositions(final Long versionId, final String keyId);

	/**
	 *
	 * @param versionId
	 * @param keyId
	 * @param positionKeyId
	 * @return 
	 * @deprecated use {@link PositionWriteFunctions#addPosition(java.lang.Long, java.lang.String, java.lang.String) } instead.
	 */
	@Deprecated
	DataResponse<T> addPosition(final Long versionId, final String keyId, final String positionKeyId);

	/**
	 *
	 * @param versionId
	 * @param keyId
	 * @param positionKeyId
	 * @return 
	 * @deprecated use {@link PositionWriteFunctions#removePosition(java.lang.Long, java.lang.String, java.lang.String) } instead.
	 */
	@Deprecated
	DataResponse<T> removePosition(final Long versionId, final String keyId, final String positionKeyId);

}

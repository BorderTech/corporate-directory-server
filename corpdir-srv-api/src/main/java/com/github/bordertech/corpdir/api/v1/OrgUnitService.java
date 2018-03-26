package com.github.bordertech.corpdir.api.v1;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicVersionTreeService;
import com.github.bordertech.corpdir.api.v1.func.PositionFunctions;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.readonly.api.v1.OrgUnitReadOnlyService;

/**
 * Organisation Unit Service Interface.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 
 * @deprecated split into read and write
 */
@Deprecated
public interface OrgUnitService extends BasicVersionTreeService<OrgUnit>, PositionFunctions<OrgUnit> {

	/**
	 * 
	 * @param keyId
	 * @return 
	 * @deprecated use {@link OrgUnitReadOnlyService#getManagerPosition(java.lang.String) } instead.
	 */
	@Deprecated
	DataResponse<Position> getManagerPosition(final String keyId);

	/**
	 * 
	 * @param versionId
	 * @param keyId
	 * @return 
	 * @deprecated use {@link OrgUnitReadOnlyService#getManagerPosition(java.lang.Long, java.lang.String) } instead.
	 */
	@Deprecated
	DataResponse<Position> getManagerPosition(final Long versionId, final String keyId);

}

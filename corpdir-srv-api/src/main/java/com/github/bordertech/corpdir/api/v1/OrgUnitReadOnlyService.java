package com.github.bordertech.corpdir.api.v1;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicVersionTreeReadOnlyService;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.api.v1.func.PositionReadOnlyFunctions;

/**
 * Organisation Unit read-only Service Interface.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface OrgUnitReadOnlyService extends BasicVersionTreeReadOnlyService<OrgUnit>, PositionReadOnlyFunctions<OrgUnit> {

	DataResponse<Position> getManagerPosition(final String keyId);

	DataResponse<Position> getManagerPosition(final Long versionId, final String keyId);
}

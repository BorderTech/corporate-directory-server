package com.github.bordertech.corpdir.api.v1;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicTreeVersionService;
import com.github.bordertech.corpdir.api.v1.func.PositionFunctions;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.api.v1.model.Position;

/**
 * Organisation Unit Service Interface.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public interface OrgUnitService extends BasicTreeVersionService<OrgUnit>, PositionFunctions<OrgUnit> {

	DataResponse<Position> getManagerPosition(final String keyId);

	DataResponse<Position> getManagerPosition(final Integer versionId, final String keyId);

}

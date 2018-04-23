package com.github.bordertech.corpdir.api.v1;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicVersionTreeReadOnlyService;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.api.v1.func.ContactReadOnlyFunctions;
import java.util.List;

/**
 * Position read-only Service Interface.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface PositionReadOnlyService extends BasicVersionTreeReadOnlyService<Position>, ContactReadOnlyFunctions<Position> {

	DataResponse<List<OrgUnit>> getManages(final String keyId);

	DataResponse<List<OrgUnit>> getManages(final Long versionId, final String keyId);

}

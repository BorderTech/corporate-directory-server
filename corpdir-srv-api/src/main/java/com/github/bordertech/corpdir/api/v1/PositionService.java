package com.github.bordertech.corpdir.api.v1;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicVersionTreeService;
import com.github.bordertech.corpdir.api.v1.func.ContactFunctions;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.modify.api.v1.PositionWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.PositionReadOnlyService;
import java.util.List;

/**
 * Position Service Interface.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 * @deprecated split into {@link PositionReadOnlyService} and {@link PositionWriteService}
 */
@Deprecated
public interface PositionService extends BasicVersionTreeService<Position>, ContactFunctions<Position> {

	@Deprecated
	DataResponse<List<OrgUnit>> getManages(final String keyId);

	@Deprecated
	DataResponse<List<OrgUnit>> getManages(final Long versionId, final String keyId);

}

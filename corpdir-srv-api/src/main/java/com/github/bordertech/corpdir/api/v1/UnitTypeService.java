package com.github.bordertech.corpdir.api.v1;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicKeyIdService;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.api.v1.model.UnitType;
import com.github.bordertech.corpdir.modify.api.v1.UnitTypeWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.UnitTypeReadOnlyService;
import java.util.List;

/**
 * Organisation Unit Type Service Interface.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 * @deprecated split into {@link UnitTypeReadOnlyService} and {@link UnitTypeWriteService}
 */
@Deprecated
public interface UnitTypeService extends BasicKeyIdService<UnitType> {

	@Deprecated
	DataResponse<List<OrgUnit>> getOrgUnits(final String keyId);

}

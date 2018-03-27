package com.github.bordertech.corpdir.api.v1;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicKeyIdService;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.api.v1.model.UnitType;
import com.github.bordertech.corpdir.readonly.api.v1.UnitTypeReadOnlyService;
import java.util.List;

/**
 * Organisation Unit Type Service Interface.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 * @deprecated split into read and write
 */
@Deprecated
public interface UnitTypeService extends BasicKeyIdService<UnitType> {

	/**
	 * 
	 * @param keyId
	 * @return 
	 * @deprecated use {@link UnitTypeReadOnlyService#getOrgUnits(java.lang.String) } instead.
	 */
	DataResponse<List<OrgUnit>> getOrgUnits(final String keyId);

}

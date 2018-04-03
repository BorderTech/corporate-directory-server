package com.github.bordertech.corpdir.readonly.api.v1;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.readonly.service.BasicKeyIdReadOnlyService;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.api.v1.model.UnitType;
import java.util.List;

/**
 * Organisation Unit Type read-only Service Interface.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface UnitTypeReadOnlyService extends BasicKeyIdReadOnlyService<UnitType> {

	DataResponse<List<OrgUnit>> getOrgUnits(final String keyId);

}

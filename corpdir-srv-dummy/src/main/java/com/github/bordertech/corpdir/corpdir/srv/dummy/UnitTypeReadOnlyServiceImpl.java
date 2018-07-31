package com.github.bordertech.corpdir.corpdir.srv.dummy;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.UnitTypeReadOnlyService;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.api.v1.model.UnitType;
import java.util.Arrays;
import java.util.List;

/**
 * Organization unit type read-only dummy service implementation.
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public class UnitTypeReadOnlyServiceImpl implements UnitTypeReadOnlyService {

	@Override
	public DataResponse<List<OrgUnit>> getOrgUnits(String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<UnitType>> search(String search) {
		UnitType dummy = new UnitType(null);
		dummy.setCustom(false);
		dummy.setActive(true);
		dummy.setBusinessKey("UT-1");
		dummy.setDescription("UT-D");
		return new DataResponse<>(Arrays.asList(dummy));
	}

	@Override
	public DataResponse<UnitType> retrieve(String id) {
		return new DataResponse<>(search(id).getData().get(0));
	}
	
}

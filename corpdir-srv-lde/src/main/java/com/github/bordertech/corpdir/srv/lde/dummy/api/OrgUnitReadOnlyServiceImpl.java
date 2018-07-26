package com.github.bordertech.corpdir.srv.lde.dummy.api;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.OrgUnitReadOnlyService;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.api.v1.model.Position;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aswinkandula
 */
public class OrgUnitReadOnlyServiceImpl implements OrgUnitReadOnlyService {

	@Override
	public DataResponse<Position> getManagerPosition(String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<Position> getManagerPosition(Long versionId, String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<OrgUnit>> getSubs(Long versionId, String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<OrgUnit>> getRootItems(Long versionId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<OrgUnit>> search(Long versionId, String search) {
		return search(search);
	}

	@Override
	public DataResponse<OrgUnit> retrieve(Long versionId, String keyId) {
		return new DataResponse<>(search(keyId).getData().get(0));
	}

	@Override
	public DataResponse<List<OrgUnit>> search(String search) {
		OrgUnit dummy = new OrgUnit(null) {{
			setCustom(false);
			setActive(true);
			setBusinessKey("OU-1");
			setDescription("OU-D");
			setTypeId("UT-1");
		}};
		return new DataResponse<>(Arrays.asList(dummy));
	}

	@Override
	public DataResponse<OrgUnit> retrieve(String id) {
		return new DataResponse<>(search(id).getData().get(0));
	}

	@Override
	public DataResponse<List<OrgUnit>> getSubs(String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<OrgUnit>> getRootItems() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<Position>> getPositions(String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<Position>> getPositions(Long versionId, String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}

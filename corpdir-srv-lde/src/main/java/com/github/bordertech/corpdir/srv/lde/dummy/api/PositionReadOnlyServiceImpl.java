package com.github.bordertech.corpdir.srv.lde.dummy.api;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.PositionReadOnlyService;
import com.github.bordertech.corpdir.api.v1.model.Contact;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.api.v1.model.Position;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aswinkandula
 */
public class PositionReadOnlyServiceImpl implements PositionReadOnlyService {

	@Override
	public DataResponse<List<OrgUnit>> getManages(String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<OrgUnit>> getManages(Long versionId, String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<Position>> getSubs(Long versionId, String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<Position>> getRootItems(Long versionId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<Position>> search(Long versionId, String search) {
		return search(search);
	}

	@Override
	public DataResponse<Position> retrieve(Long versionId, String keyId) {
		return new DataResponse<>(search(keyId).getData().get(0));
	}

	@Override
	public DataResponse<List<Position>> search(String search) {
		Position dummy = new Position(null) {{
			setCustom(false);
			setActive(true);
			setBusinessKey("PO-1");
			setDescription("PO-D");
			setTypeId("PT-1");
			setOuId("OU-1");
			getManageOuIds().add("PU-1");
		}};
		return new DataResponse<>(Arrays.asList(dummy));
	}

	@Override
	public DataResponse<Position> retrieve(String id) {
		return new DataResponse<>(search(id).getData().get(0));
	}

	@Override
	public DataResponse<List<Position>> getSubs(String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<Position>> getRootItems() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<Contact>> getContacts(String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<Contact>> getContacts(Long versionId, String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}

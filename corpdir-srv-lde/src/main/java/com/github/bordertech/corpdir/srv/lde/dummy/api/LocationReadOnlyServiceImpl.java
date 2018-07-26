package com.github.bordertech.corpdir.srv.lde.dummy.api;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.LocationReadOnlyService;
import com.github.bordertech.corpdir.api.v1.model.Location;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aswinkandula
 */
public class LocationReadOnlyServiceImpl implements LocationReadOnlyService {

	@Override
	public DataResponse<List<Location>> getSubs(String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<Location>> getRootItems() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<Location>> search(String search) {
		Location dummy = new Location(null);
		dummy.setCustom(false);
		dummy.setActive(true);
		dummy.setBusinessKey("LO-1");
		dummy.setDescription("LO-D");
		return new DataResponse<>(Arrays.asList(dummy));
	}

	@Override
	public DataResponse<Location> retrieve(String id) {
		return new DataResponse<>(search(id).getData().get(0));
	}
	
}

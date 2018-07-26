package com.github.bordertech.corpdir.srv.lde.dummy.api;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.PositionTypeReadOnlyService;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.api.v1.model.PositionType;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aswinkandula
 */
public class PositionTypeReadOnlyServiceImpl implements PositionTypeReadOnlyService {

	@Override
	public DataResponse<List<Position>> getPositions(String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<PositionType>> search(String search) {
		PositionType dummy = new PositionType(null) {{
			setCustom(false);
			setActive(true);
			setBusinessKey("PT-1");
			setDescription("PT-D");
		}};
		return new DataResponse<>(Arrays.asList(dummy));
	}

	@Override
	public DataResponse<PositionType> retrieve(String id) {
		return new DataResponse<>(search(id).getData().get(0));
	}
	
}

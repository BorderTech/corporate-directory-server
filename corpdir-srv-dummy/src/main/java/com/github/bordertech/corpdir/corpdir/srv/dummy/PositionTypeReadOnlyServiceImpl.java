package com.github.bordertech.corpdir.corpdir.srv.dummy;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.PositionTypeReadOnlyService;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.api.v1.model.PositionType;
import java.util.Arrays;
import java.util.List;

/**
 * Position type read-only dummy service implementation.
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public class PositionTypeReadOnlyServiceImpl implements PositionTypeReadOnlyService {

	@Override
	public DataResponse<List<Position>> getPositions(String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<PositionType>> search(String search) {
		PositionType dummy = new PositionType(null);
		dummy.setCustom(false);
		dummy.setActive(true);
		dummy.setBusinessKey("PT-1");
		dummy.setDescription("PT-D");
		return new DataResponse<>(Arrays.asList(dummy));
	}

	@Override
	public DataResponse<PositionType> retrieve(String id) {
		return new DataResponse<>(search(id).getData().get(0));
	}
	
}

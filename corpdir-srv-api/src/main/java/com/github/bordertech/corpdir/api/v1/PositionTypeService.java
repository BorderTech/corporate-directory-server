package com.github.bordertech.corpdir.api.v1;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicKeyIdService;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.api.v1.model.PositionType;
import com.github.bordertech.corpdir.readonly.api.v1.PositionTypeReadOnlyService;
import java.util.List;

/**
 * Position Type Service Interface.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 * @deprecated split into read and write
 */
@Deprecated
public interface PositionTypeService extends BasicKeyIdService<PositionType> {

	/**
	 * 
	 * @param keyId
	 * @return 
	 * @deprecated use {@link PositionTypeReadOnlyService#getPositions(java.lang.String) } instead.
	 */
	@Deprecated
	DataResponse<List<Position>> getPositions(final String keyId);

}

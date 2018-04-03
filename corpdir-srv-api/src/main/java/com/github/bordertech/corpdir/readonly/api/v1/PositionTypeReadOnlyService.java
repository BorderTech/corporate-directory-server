package com.github.bordertech.corpdir.readonly.api.v1;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.readonly.service.BasicKeyIdReadOnlyService;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.api.v1.model.PositionType;
import java.util.List;

/**
 * Position Type read-only Service Interface.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface PositionTypeReadOnlyService extends BasicKeyIdReadOnlyService<PositionType> {

	DataResponse<List<Position>> getPositions(final String keyId);

}

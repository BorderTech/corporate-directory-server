package com.github.bordertech.corpdir.sync;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.PositionReadOnlyService;
import com.github.bordertech.corpdir.api.v1.PositionService;
import com.github.bordertech.corpdir.api.v1.model.Position;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author exiqaj
 */
public class PositionSynchronisation extends AbstractVersionSynchronisation<PositionReadOnlyService, PositionService, Position> {
	
	@Inject
	public PositionSynchronisation(PositionReadOnlyService sourceService, PositionService destinationService) {
		super(sourceService, destinationService);
	}
	
	@Override
	public void syncBaseData() {
		//TODO getManageOuIds, PositionType
		DataResponse<List<Position>> sourcePositions = getSourceData();
		final Long versionId = Long.parseLong(getOrCreateNewVersion());
		for (Position sourcePosition : sourcePositions.getData()) {
			createOrUpdateData(versionId, sourcePosition);

		}
		
	}
	
	@Override
    public void syncLinkedData() {
		// TODO
	}

}

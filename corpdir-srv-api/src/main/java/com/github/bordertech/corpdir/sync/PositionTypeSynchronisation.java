package com.github.bordertech.corpdir.sync;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.PositionTypeReadOnlyService;
import com.github.bordertech.corpdir.api.v1.PositionTypeService;
import com.github.bordertech.corpdir.api.v1.model.PositionType;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author exiqaj
 */
public class PositionTypeSynchronisation extends AbstractSynchronisation<PositionTypeReadOnlyService, PositionTypeService, PositionType> {

    @Inject
    public PositionTypeSynchronisation(PositionTypeReadOnlyService sourceService, PositionTypeService destinationService) {
	super(sourceService, destinationService);
    }

    @Override
    public void syncBaseData() {
	DataResponse<List<PositionType>> sourcePositionTypes = getSourceData();

	for (PositionType sourcePositionType : sourcePositionTypes.getData()) {
	    createOrUpdateData(sourcePositionType);
	}
    }

}

package com.github.bordertech.corpdir.sync;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.UnitTypeReadOnlyService;
import com.github.bordertech.corpdir.api.v1.UnitTypeService;
import com.github.bordertech.corpdir.api.v1.model.UnitType;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author exiqaj
 */
public class UnitLevelSynchronisation extends AbstractSynchronisation<UnitTypeReadOnlyService, UnitTypeService, UnitType> {

    @Inject
    public UnitLevelSynchronisation(UnitTypeReadOnlyService sourceService, UnitTypeService destinationService) {
	super(sourceService, destinationService);
    }

    @Override
    public void syncBaseData() {
	DataResponse<List<UnitType>> sourceUnitTypes = getSourceData();

	for (UnitType sourceUnitType : sourceUnitTypes.getData()) {
	    createOrUpdateData(sourceUnitType);
	}
    }

}

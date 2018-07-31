package com.github.bordertech.corpdir.sync.impl;

import com.github.bordertech.corpdir.api.v1.UnitTypeReadOnlyService;
import com.github.bordertech.corpdir.api.v1.UnitTypeService;
import com.github.bordertech.corpdir.api.v1.model.UnitType;
import com.github.bordertech.corpdir.sync.common.AbstractSynchronisation;
import javax.inject.Inject;

/**
 *
 * @author aswinkandula
 */
public class UnitLevelSynchronisation extends AbstractSynchronisation<UnitTypeReadOnlyService, UnitTypeService, UnitType> {

	@Inject
	public UnitLevelSynchronisation(UnitTypeReadOnlyService sourceService, UnitTypeService destinationService) {
		super(sourceService, destinationService);
	}

}

package com.github.bordertech.corpdir.sync.apis;

import com.github.bordertech.corpdir.api.v1.UnitTypeReadOnlyService;
import com.github.bordertech.corpdir.api.v1.UnitTypeService;
import com.github.bordertech.corpdir.api.v1.model.UnitType;
import com.github.bordertech.corpdir.sync.common.AbstractKeyIdSynchronisation;
import javax.inject.Inject;

/**
 * One-way organisation unit type synchronisation.
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public class UnitLevelSynchronisation extends AbstractKeyIdSynchronisation<UnitTypeReadOnlyService, UnitTypeService, UnitType> {

	@Inject
	public UnitLevelSynchronisation(UnitTypeReadOnlyService sourceService, UnitTypeService destinationService) {
		super(sourceService, destinationService);
	}

}

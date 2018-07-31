package com.github.bordertech.corpdir.sync.impl;

import com.github.bordertech.corpdir.api.v1.PositionTypeReadOnlyService;
import com.github.bordertech.corpdir.api.v1.PositionTypeService;
import com.github.bordertech.corpdir.api.v1.model.PositionType;
import com.github.bordertech.corpdir.sync.common.AbstractSynchronisation;
import javax.inject.Inject;

/**
 * One-way position type synchronisation.
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public class PositionTypeSynchronisation extends AbstractSynchronisation<PositionTypeReadOnlyService, PositionTypeService, PositionType> {

	@Inject
	public PositionTypeSynchronisation(PositionTypeReadOnlyService sourceService, PositionTypeService destinationService) {
		super(sourceService, destinationService);
	}

}

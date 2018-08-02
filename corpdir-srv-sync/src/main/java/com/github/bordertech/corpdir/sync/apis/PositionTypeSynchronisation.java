package com.github.bordertech.corpdir.sync.apis;

import com.github.bordertech.corpdir.api.v1.PositionTypeReadOnlyService;
import com.github.bordertech.corpdir.api.v1.PositionTypeService;
import com.github.bordertech.corpdir.api.v1.model.PositionType;
import com.github.bordertech.corpdir.sync.common.AbstractKeyIdSynchronisation;
import javax.inject.Inject;

/**
 * One-way position type synchronisation.
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public class PositionTypeSynchronisation extends AbstractKeyIdSynchronisation<PositionTypeReadOnlyService, PositionTypeService, PositionType> {

	@Inject
	public PositionTypeSynchronisation(PositionTypeReadOnlyService sourceService, PositionTypeService destinationService) {
		super(sourceService, destinationService);
	}

}

package com.github.bordertech.corpdir.sync.impl;

import com.github.bordertech.corpdir.api.v1.PositionReadOnlyService;
import com.github.bordertech.corpdir.api.v1.PositionService;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.sync.common.AbstractVersionSynchronisation;
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

	//TODO getManageOuIds, PositionType

}

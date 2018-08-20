package com.github.bordertech.corpdir.sync.apis;

import com.github.bordertech.corpdir.api.v1.PositionReadOnlyService;
import com.github.bordertech.corpdir.api.v1.PositionService;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.sync.api.mapper.PositionMapper;
import com.github.bordertech.corpdir.sync.common.AbstractVersionKeyIdSynchronisation;
import javax.inject.Inject;

/**
 * One-way position synchronisation.
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public class PositionSynchronisation extends AbstractVersionKeyIdSynchronisation<Position> {

	private static final PositionMapper API_MAPPER = new PositionMapper();

	@Inject
	public PositionSynchronisation(final PositionReadOnlyService sourceService, final PositionService destinationService) {
		super(sourceService, destinationService);
	}

	//TODO getManageOuIds, PositionType

	@Override
	public PositionMapper getApiMapper() {
		return API_MAPPER;
	}



}

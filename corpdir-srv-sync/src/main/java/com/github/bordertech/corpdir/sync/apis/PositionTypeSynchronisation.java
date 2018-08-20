package com.github.bordertech.corpdir.sync.apis;

import com.github.bordertech.corpdir.api.v1.PositionTypeReadOnlyService;
import com.github.bordertech.corpdir.api.v1.PositionTypeService;
import com.github.bordertech.corpdir.api.v1.model.PositionType;
import com.github.bordertech.corpdir.sync.api.mapper.PositionTypeMapper;
import com.github.bordertech.corpdir.sync.common.AbstractKeyIdSynchronisation;
import javax.inject.Inject;

/**
 * One-way position type synchronisation.
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public class PositionTypeSynchronisation extends AbstractKeyIdSynchronisation<PositionType> {

	private static final PositionTypeMapper API_MAPPER = new PositionTypeMapper();

	@Inject
	public PositionTypeSynchronisation(final PositionTypeReadOnlyService sourceService, final PositionTypeService destinationService) {
		super(sourceService, destinationService);
	}

	@Override
	public PositionTypeMapper getApiMapper() {
		return API_MAPPER;
	}

}

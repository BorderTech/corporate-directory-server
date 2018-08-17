package com.github.bordertech.corpdir.sync.apis;

import com.github.bordertech.corpdir.api.v1.UnitTypeReadOnlyService;
import com.github.bordertech.corpdir.api.v1.UnitTypeService;
import com.github.bordertech.corpdir.api.v1.model.UnitType;
import com.github.bordertech.corpdir.sync.api.mapper.UnitMapper;
import com.github.bordertech.corpdir.sync.common.AbstractKeyIdSynchronisation;
import javax.inject.Inject;

/**
 * One-way organisation unit type synchronisation.
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public class UnitTypeSynchronisation extends AbstractKeyIdSynchronisation<UnitType> {

	private static final UnitMapper API_MAPPER = new UnitMapper();

	@Inject
	public UnitTypeSynchronisation(final UnitTypeReadOnlyService sourceService, final UnitTypeService destinationService) {
		super(sourceService, destinationService);
	}

	@Override
	public UnitMapper getApiMapper() {
		return API_MAPPER;
	}

}

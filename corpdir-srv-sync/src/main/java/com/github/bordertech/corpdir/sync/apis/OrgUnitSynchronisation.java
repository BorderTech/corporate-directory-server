package com.github.bordertech.corpdir.sync.apis;

import com.github.bordertech.corpdir.api.v1.OrgUnitReadOnlyService;
import com.github.bordertech.corpdir.api.v1.OrgUnitService;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.sync.api.mapper.OrgUnitMapper;
import com.github.bordertech.corpdir.sync.common.AbstractVersionKeyIdSynchronisation;
import javax.inject.Inject;

/**
 * One-way organisation unit synchronisation
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public class OrgUnitSynchronisation extends AbstractVersionKeyIdSynchronisation<OrgUnit, OrgUnitReadOnlyService, OrgUnitService, OrgUnitMapper> {

	public static final OrgUnitMapper API_MAPPER = new OrgUnitMapper();

	@Inject
	public OrgUnitSynchronisation(OrgUnitReadOnlyService sourceService, OrgUnitService destinationService) {
		super(sourceService, destinationService);
	}

	@Override
	public OrgUnitMapper getApiMapper() {
		return API_MAPPER;
	}
}

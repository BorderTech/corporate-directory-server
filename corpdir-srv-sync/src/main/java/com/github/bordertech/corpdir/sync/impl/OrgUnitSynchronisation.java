package com.github.bordertech.corpdir.sync.impl;

import com.github.bordertech.corpdir.api.v1.OrgUnitReadOnlyService;
import com.github.bordertech.corpdir.api.v1.OrgUnitService;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.sync.common.AbstractVersionSynchronisation;
import javax.inject.Inject;

/**
 * One-way organisation unit synchronisation
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public class OrgUnitSynchronisation extends AbstractVersionSynchronisation<OrgUnitReadOnlyService, OrgUnitService, OrgUnit> {

	@Inject
	public OrgUnitSynchronisation(OrgUnitReadOnlyService sourceService, OrgUnitService destinationService) {
		super(sourceService, destinationService);
	}
}

package com.github.bordertech.corpdir.sync.impl;

import com.github.bordertech.corpdir.sync.common.AbstractVersionSynchronisation;
import com.github.bordertech.corpdir.api.v1.OrgUnitReadOnlyService;
import com.github.bordertech.corpdir.api.v1.OrgUnitService;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import javax.inject.Inject;

/**
 * One-way Organisation Units synchronisation from Source to Destination
 *
 * @author exiqaj
 */
public class OrgUnitSynchronisation extends AbstractVersionSynchronisation<OrgUnitReadOnlyService, OrgUnitService, OrgUnit> {

	@Inject
	public OrgUnitSynchronisation(OrgUnitReadOnlyService sourceService, OrgUnitService destinationService) {
		super(sourceService, destinationService);
	}

	@Override
	public void syncLinkedData() {
		// TODO
	}
}

package com.github.bordertech.corpdir.sync;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.OrgUnitReadOnlyService;
import com.github.bordertech.corpdir.api.v1.OrgUnitService;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import java.util.List;
import javax.inject.Inject;

/**
 * One-way Organisation Units synchronisation from Source to Destination
 * @author exiqaj
 */
public class OrgUnitSynchronisation extends AbstractVersionSynchronisation<OrgUnitReadOnlyService, OrgUnitService, OrgUnit> {
    
	@Inject
	public OrgUnitSynchronisation(OrgUnitReadOnlyService sourceService, OrgUnitService destinationService) {
		super(sourceService, destinationService);
	}
	
	@Override
    public void syncBaseData() {
		DataResponse<List<OrgUnit>> sourceOrgUnits = getSourceData();
		final Long versionId = Long.parseLong(getOrCreateNewVersion());
		for (OrgUnit sourceOrgUnit : sourceOrgUnits.getData()) {
			createOrUpdateData(versionId, sourceOrgUnit);
		}
    }
	
	@Override
    public void syncLinkedData() {
		// TODO
	}
}

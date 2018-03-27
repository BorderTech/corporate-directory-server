package com.github.bordertech.corpdir.web.ui.flux.dataapi.impl;

import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.modify.api.v1.OrgUnitWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.OrgUnitReadOnlyService;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.DefaultCorpCrudTreeVersionDataApi;
import javax.inject.Inject;

/**
 * OrgUnit CRUD API implementation.
 *
 * @author jonathan
 */
public class OrgUnitApi extends DefaultCorpCrudTreeVersionDataApi<OrgUnit, OrgUnitReadOnlyService, OrgUnitWriteService> {

	@Inject
	public OrgUnitApi(final OrgUnitReadOnlyService readService, final OrgUnitWriteService writeService) {
		super(OrgUnit.class, readService, writeService);
	}

}

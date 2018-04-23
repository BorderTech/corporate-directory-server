package com.github.bordertech.corpdir.web.ui.flux.dataapi.impl;

import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.DefaultCorpCrudTreeVersionDataApi;
import javax.inject.Inject;
import com.github.bordertech.corpdir.api.v1.OrgUnitService;

/**
 * OrgUnit CRUD API implementation.
 *
 * @author jonathan
 */
public class OrgUnitApi extends DefaultCorpCrudTreeVersionDataApi<OrgUnit, OrgUnitService> {

	@Inject
	public OrgUnitApi(final OrgUnitService service) {
		super(OrgUnit.class, service);
	}

}

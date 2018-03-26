package com.github.bordertech.corpdir.web.ui.flux.store.impl;

import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.web.ui.CorpEntityType;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.impl.OrgUnitApi;
import com.github.bordertech.corpdir.web.ui.flux.store.temp.DefaultCorpCrudTreeVersionStoreTemp;
import javax.inject.Inject;

/**
 * OrgUnit Store with backing API implementation.
 *
 * @author jonathan
 */
public class OrgUnitStore extends DefaultCorpCrudTreeVersionStoreTemp<OrgUnit, OrgUnitApi> {

	/**
	 * @param api the backing API
	 */
	@Inject
	public OrgUnitStore(final OrgUnitApi api) {
		super(CorpEntityType.ORG_UNIT, api);
	}
}

package com.github.bordertech.corpdir.web.ui.flux.dataapi.impl;

import com.github.bordertech.corpdir.api.v1.model.UnitType;
import com.github.bordertech.corpdir.modify.api.v1.UnitTypeWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.UnitTypeReadOnlyService;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.temp.DefaultCorpCrudDataApiTemp;
import javax.inject.Inject;

/**
 * Unit Type CRUD API implementation.
 *
 * @author jonathan
 */
public class UnitTypeApi extends DefaultCorpCrudDataApiTemp<UnitType, UnitTypeReadOnlyService, UnitTypeWriteService> {

	@Inject
	public UnitTypeApi(final UnitTypeReadOnlyService readService, final UnitTypeWriteService writeService) {
		super(UnitType.class, readService, writeService);
	}
}

package com.github.bordertech.corpdir.web.ui.flux.dataapi.impl;

import com.github.bordertech.corpdir.api.v1.model.PositionType;
import com.github.bordertech.corpdir.modify.api.v1.PositionTypeWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.PositionTypeReadOnlyService;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.DefaultCorpCrudDataApi;
import javax.inject.Inject;

/**
 * PositionType CRUD API implementation.
 *
 * @author jonathan
 */
public class PositionTypeApi extends DefaultCorpCrudDataApi<PositionType, PositionTypeReadOnlyService, PositionTypeWriteService> {

	@Inject
	public PositionTypeApi(final PositionTypeReadOnlyService readService, final PositionTypeWriteService writeService) {
		super(PositionType.class, readService, writeService);
	}
}

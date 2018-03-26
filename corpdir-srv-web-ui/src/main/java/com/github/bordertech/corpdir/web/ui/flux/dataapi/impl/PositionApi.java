package com.github.bordertech.corpdir.web.ui.flux.dataapi.impl;

import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.modify.api.v1.PositionWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.PositionReadOnlyService;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.temp.DefaultCorpCrudTreeVersionDataApiTemp;
import javax.inject.Inject;

/**
 * Position CRUD API implementation.
 *
 * @author jonathan
 */
public class PositionApi extends DefaultCorpCrudTreeVersionDataApiTemp<Position, PositionReadOnlyService, PositionWriteService> {

	@Inject
	public PositionApi(final PositionReadOnlyService readService, final PositionWriteService writeService) {
		super(Position.class, readService, writeService);
	}

}

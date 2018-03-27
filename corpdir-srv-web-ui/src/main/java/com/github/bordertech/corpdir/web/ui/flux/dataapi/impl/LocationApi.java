package com.github.bordertech.corpdir.web.ui.flux.dataapi.impl;

import com.github.bordertech.corpdir.api.v1.model.Location;
import com.github.bordertech.corpdir.modify.api.v1.LocationWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.LocationReadOnlyService;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.DefaultCorpCrudTreeDataApi;
import javax.inject.Inject;

/**
 * Location CRUD API implementation.
 *
 * @author jonathan
 */
public class LocationApi extends DefaultCorpCrudTreeDataApi<Location, LocationReadOnlyService, LocationWriteService> {

	@Inject
	public LocationApi(final LocationReadOnlyService readService, final LocationWriteService writeService) {
		super(Location.class, readService, writeService);
	}

}

package com.github.bordertech.corpdir.web.ui.flux.store.impl;

import com.github.bordertech.corpdir.api.v1.model.Location;
import com.github.bordertech.corpdir.web.ui.CorpEntityType;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.impl.LocationApi;
import com.github.bordertech.corpdir.web.ui.flux.store.temp.DefaultCorpCrudTreeStoreTemp;
import javax.inject.Inject;

/**
 * Location Store with backing API implementation.
 *
 * @author jonathan
 */
public class LocationStore extends DefaultCorpCrudTreeStoreTemp<Location, LocationApi> {

	/**
	 * @param api the backing API
	 */
	@Inject
	public LocationStore(final LocationApi api) {
		super(CorpEntityType.LOCATION, api);
	}
}

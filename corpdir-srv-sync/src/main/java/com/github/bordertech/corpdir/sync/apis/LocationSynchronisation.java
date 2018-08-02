package com.github.bordertech.corpdir.sync.apis;

import com.github.bordertech.corpdir.api.v1.LocationReadOnlyService;
import com.github.bordertech.corpdir.api.v1.LocationService;
import com.github.bordertech.corpdir.api.v1.model.Location;
import com.github.bordertech.corpdir.sync.common.AbstractKeyIdSynchronisation;
import javax.inject.Inject;

/**
 * One-way Location synchronisation.
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public class LocationSynchronisation extends AbstractKeyIdSynchronisation<LocationReadOnlyService, LocationService, Location> {

	@Inject
	public LocationSynchronisation(LocationReadOnlyService sourceService, LocationService destinationService) {
		super(sourceService, destinationService);
	}

}

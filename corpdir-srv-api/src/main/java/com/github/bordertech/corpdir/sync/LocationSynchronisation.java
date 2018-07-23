package com.github.bordertech.corpdir.sync;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.LocationReadOnlyService;
import com.github.bordertech.corpdir.api.v1.LocationService;
import com.github.bordertech.corpdir.api.v1.model.Location;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author exiqaj
 */
public class LocationSynchronisation extends AbstractSynchronisation<LocationReadOnlyService, LocationService, Location> {

	@Inject
	public LocationSynchronisation(LocationReadOnlyService sourceService, LocationService destinationService) {
		super(sourceService, destinationService);
	}

	@Override
	public void syncBaseData() {
		DataResponse<List<Location>> sourceLocations = getSourceData();
		
		for (Location sourceLocation : sourceLocations.getData()) {
			createOrUpdateData(sourceLocation);
		}
	}
	
}
package com.github.bordertech.corpdir.jpa.readonly.v1.api;

import com.github.bordertech.corpdir.api.v1.model.Location;
import com.github.bordertech.corpdir.jpa.common.map.MapperApi;
import com.github.bordertech.corpdir.jpa.entity.LocationEntity;
import com.github.bordertech.corpdir.jpa.readonly.common.svc.JpaBasicTreeReadOnlyService;
import com.github.bordertech.corpdir.jpa.v1.mapper.LocationMapper;
import com.github.bordertech.corpdir.readonly.api.v1.LocationReadOnlyService;
import javax.inject.Singleton;

/**
 * Location JPA read-only service implementation.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public class LocationReadOnlyServiceImpl extends JpaBasicTreeReadOnlyService<Location, LocationEntity> implements LocationReadOnlyService {

	private static final LocationMapper LOCATION_MAPPER = new LocationMapper();

	@Override
	protected Class<LocationEntity> getEntityClass() {
		return LocationEntity.class;
	}

	@Override
	protected MapperApi<Location, LocationEntity> getMapper() {
		return LOCATION_MAPPER;
	}
    
}

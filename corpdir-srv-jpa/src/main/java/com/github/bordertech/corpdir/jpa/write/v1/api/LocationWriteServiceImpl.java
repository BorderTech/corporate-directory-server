package com.github.bordertech.corpdir.jpa.write.v1.api;

import com.github.bordertech.corpdir.api.v1.model.Location;
import com.github.bordertech.corpdir.jpa.common.map.MapperApi;
import com.github.bordertech.corpdir.jpa.entity.LocationEntity;
import com.github.bordertech.corpdir.jpa.modify.common.svc.JpaBasicTreeWriteService;
import com.github.bordertech.corpdir.jpa.v1.mapper.LocationMapper;
import com.github.bordertech.corpdir.modify.api.v1.LocationWriteService;
import javax.inject.Singleton;

/**
 * Location JPA write (modifiable) service implementation.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public class LocationWriteServiceImpl extends JpaBasicTreeWriteService<Location, LocationEntity> implements LocationWriteService {

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

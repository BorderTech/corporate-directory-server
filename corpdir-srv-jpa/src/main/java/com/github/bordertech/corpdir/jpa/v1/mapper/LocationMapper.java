package com.github.bordertech.corpdir.jpa.v1.mapper;

import com.github.bordertech.corpdir.api.v1.model.Location;
import com.github.bordertech.corpdir.jpa.common.AbstractKeyIdApiEntityMapper;
import com.github.bordertech.corpdir.jpa.entity.LocationEntity;
import com.github.bordertech.corpdir.jpa.util.MapperUtil;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Map {@link Location} and {@link LocationEntity}.
 *
 * @author jonathan
 */
public class LocationMapper extends AbstractKeyIdApiEntityMapper<Location, LocationEntity> {

	private static final AddressMapper ADDRESS_MAPPER = new AddressMapper();

	@Override
	public void copyApiToEntityFields(final EntityManager em, final Location from, final LocationEntity to) {
		to.setDescription(from.getDescription());
		to.setAddress(ADDRESS_MAPPER.convertApiToEntity(em, from.getAddress()));

		// Parent Location
		String origId = MapperUtil.getEntityBusinessKey(to.getParentLocation());
		String newId = from.getParentKey();
		if (!MapperUtil.keyMatch(origId, newId)) {
			// Remove from Orig Parent
			if (origId != null) {
				LocationEntity loc = getLocationEntity(em, origId);
				loc.removeSubLocation(to);
			}
			// Add to New Parent
			if (newId != null) {
				LocationEntity loc = getLocationEntity(em, newId);
				loc.addSubLocation(to);
			}
		}

		// Sub Locations
		List<String> origIds = MapperUtil.convertEntitiesToApiKeys(to.getSubLocations());
		List<String> newIds = from.getSubKeys();
		if (!MapperUtil.keysMatch(newIds, origIds)) {
			// Removed
			for (String id : MapperUtil.keysRemoved(origIds, newIds)) {
				LocationEntity loc = getLocationEntity(em, id);
				to.removeSubLocation(loc);
			}
			// Added
			for (String id : MapperUtil.keysAdded(origIds, newIds)) {
				LocationEntity loc = getLocationEntity(em, id);
				to.addSubLocation(loc);
			}
		}

	}

	@Override
	public void copyEntityToApiFields(final EntityManager em, final LocationEntity from, final Location to) {
		to.setDescription(from.getDescription());
		to.setAddress(ADDRESS_MAPPER.convertEntityToApi(em, from.getAddress()));
		to.setParentKey(MapperUtil.getEntityBusinessKey(from.getParentLocation()));
		to.setSubKeys(MapperUtil.convertEntitiesToApiKeys(from.getSubLocations()));
	}

	@Override
	protected Location createApiObject() {
		return new Location();
	}

	@Override
	protected LocationEntity createEntityObject(final Long id, final String businessKey) {
		return new LocationEntity(id, businessKey);
	}

	protected LocationEntity getLocationEntity(final EntityManager em, final String keyId) {
		return MapperUtil.getEntity(em, keyId, LocationEntity.class);
	}

}

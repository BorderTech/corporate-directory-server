package com.github.bordertech.corpdir.jpa.v1.mapper;

import com.github.bordertech.corpdir.api.v1.model.UnitType;
import com.github.bordertech.corpdir.jpa.common.map.AbstractMapperKeyId;
import com.github.bordertech.corpdir.jpa.entity.UnitTypeEntity;

/**
 * Map {@link UnitType} and {@link UnitTypeEntity}.
 *
 * @author jonathan
 */
public class UnitTypeMapper extends AbstractMapperKeyId<UnitType, UnitTypeEntity> {

	@Override
	protected UnitType createApiObject(final String id) {
		return new UnitType(id);
	}

	@Override
	protected UnitTypeEntity createEntityObject(final Long id) {
		return new UnitTypeEntity(id);
	}

}

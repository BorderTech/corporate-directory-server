package com.github.bordertech.corpdir.jpa.v1.api;

import com.github.bordertech.corpdir.api.v1.model.UnitType;
import com.github.bordertech.corpdir.jpa.common.map.MapperApi;
import com.github.bordertech.corpdir.jpa.common.svc.JpaBasicKeyIdService;
import com.github.bordertech.corpdir.jpa.entity.UnitTypeEntity;
import com.github.bordertech.corpdir.jpa.v1.mapper.OrgUnitMapper;
import com.github.bordertech.corpdir.jpa.v1.mapper.UnitTypeMapper;

/**
 * Abstract unit type service implementation.
 * 
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public abstract class UnitTypeServiceImpl extends JpaBasicKeyIdService<UnitType, UnitTypeEntity> {

	protected static final OrgUnitMapper ORGUNIT_MAPPER = new OrgUnitMapper();
	protected static final UnitTypeMapper UNITTYPE_MAPPER = new UnitTypeMapper();

	@Override
	protected Class<UnitTypeEntity> getEntityClass() {
		return UnitTypeEntity.class;
	}

	@Override
	protected MapperApi<UnitType, UnitTypeEntity> getMapper() {
		return UNITTYPE_MAPPER;
	}
}

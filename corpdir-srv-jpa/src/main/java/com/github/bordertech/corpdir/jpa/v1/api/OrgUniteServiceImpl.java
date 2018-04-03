package com.github.bordertech.corpdir.jpa.v1.api;

import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.jpa.common.map.MapperApiVersion;
import com.github.bordertech.corpdir.jpa.common.svc.JpaBasicVersionTreeService;
import com.github.bordertech.corpdir.jpa.entity.OrgUnitEntity;
import com.github.bordertech.corpdir.jpa.entity.version.OrgUnitVersionEntity;
import com.github.bordertech.corpdir.jpa.v1.mapper.OrgUnitMapper;
import com.github.bordertech.corpdir.jpa.v1.mapper.PositionMapper;

/**
 * Abstract organization unit service implementation.
 * 
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */public abstract class OrgUniteServiceImpl extends JpaBasicVersionTreeService<OrgUnit, OrgUnitVersionEntity, OrgUnitEntity> {

	protected static final PositionMapper POSITION_MAPPER = new PositionMapper();
	protected static final OrgUnitMapper ORGUNIT_MAPPER = new OrgUnitMapper();

	@Override
	protected Class<OrgUnitEntity> getEntityClass() {
		return OrgUnitEntity.class;
	}

	@Override
	protected Class<OrgUnitVersionEntity> getVersionEntityClass() {
		return OrgUnitVersionEntity.class;
	}

	@Override
	protected MapperApiVersion<OrgUnit, OrgUnitVersionEntity, OrgUnitEntity> getMapper() {
		return ORGUNIT_MAPPER;
	}
}

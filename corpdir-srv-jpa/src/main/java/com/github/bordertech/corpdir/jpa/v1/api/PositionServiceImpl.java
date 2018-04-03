package com.github.bordertech.corpdir.jpa.v1.api;

import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.jpa.common.map.MapperApiVersion;
import com.github.bordertech.corpdir.jpa.common.svc.JpaBasicVersionTreeService;
import com.github.bordertech.corpdir.jpa.entity.PositionEntity;
import com.github.bordertech.corpdir.jpa.entity.version.PositionVersionEntity;
import com.github.bordertech.corpdir.jpa.v1.mapper.ContactMapper;
import com.github.bordertech.corpdir.jpa.v1.mapper.OrgUnitMapper;
import com.github.bordertech.corpdir.jpa.v1.mapper.PositionMapper;

/**
 * Abstract position service implementation.
 * 
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public abstract class PositionServiceImpl extends JpaBasicVersionTreeService<Position, PositionVersionEntity, PositionEntity> {

	protected static final ContactMapper CONTACT_MAPPER = new ContactMapper();
	protected static final OrgUnitMapper ORGUNIT_MAPPER = new OrgUnitMapper();
	protected static final PositionMapper POSITION_MAPPER = new PositionMapper();

	@Override
	protected Class<PositionEntity> getEntityClass() {
		return PositionEntity.class;
	}

	@Override
	protected Class<PositionVersionEntity> getVersionEntityClass() {
		return PositionVersionEntity.class;
	}

	@Override
	protected MapperApiVersion<Position, PositionVersionEntity, PositionEntity> getMapper() {
		return POSITION_MAPPER;
	}
}

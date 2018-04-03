package com.github.bordertech.corpdir.jpa.v1.api;

import com.github.bordertech.corpdir.api.v1.model.PositionType;
import com.github.bordertech.corpdir.jpa.common.map.MapperApi;
import com.github.bordertech.corpdir.jpa.common.svc.JpaBasicKeyIdService;
import com.github.bordertech.corpdir.jpa.entity.PositionTypeEntity;
import com.github.bordertech.corpdir.jpa.v1.mapper.PositionMapper;
import com.github.bordertech.corpdir.jpa.v1.mapper.PositionTypeMapper;

/**
 * Abstract position type service implementation.
 * 
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public abstract class PositionTypeServiceImpl extends JpaBasicKeyIdService<PositionType, PositionTypeEntity> {

	protected static final PositionTypeMapper POSITIONTYPE_MAPPER = new PositionTypeMapper();
	protected static final PositionMapper POSITION_MAPPER = new PositionMapper();

	@Override
	protected Class<PositionTypeEntity> getEntityClass() {
		return PositionTypeEntity.class;
	}

	@Override
	protected MapperApi<PositionType, PositionTypeEntity> getMapper() {
		return POSITIONTYPE_MAPPER;
	}
}

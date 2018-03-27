package com.github.bordertech.corpdir.jpa.write.v1.api;

import com.github.bordertech.corpdir.api.v1.model.PositionType;
import com.github.bordertech.corpdir.jpa.common.map.MapperApi;
import com.github.bordertech.corpdir.jpa.entity.PositionTypeEntity;
import com.github.bordertech.corpdir.jpa.modify.common.svc.JpaBasicKeyIdWriteService;
import com.github.bordertech.corpdir.jpa.v1.mapper.PositionTypeMapper;
import com.github.bordertech.corpdir.modify.api.v1.PositionTypeWriteService;
import javax.inject.Singleton;

/**
 * Position type JPA write (modifiable) service implementation.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public class PositionTypeWriteServiceImpl extends JpaBasicKeyIdWriteService<PositionType, PositionTypeEntity> implements PositionTypeWriteService {

	private static final PositionTypeMapper POSITIONTYPE_MAPPER = new PositionTypeMapper();

	@Override
	protected Class<PositionTypeEntity> getEntityClass() {
		return PositionTypeEntity.class;
	}

	@Override
	protected MapperApi<PositionType, PositionTypeEntity> getMapper() {
		return POSITIONTYPE_MAPPER;
	}
}

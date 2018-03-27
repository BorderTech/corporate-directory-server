package com.github.bordertech.corpdir.jpa.write.v1.api;

import com.github.bordertech.corpdir.api.v1.model.UnitType;
import com.github.bordertech.corpdir.jpa.common.map.MapperApi;
import com.github.bordertech.corpdir.jpa.entity.UnitTypeEntity;
import com.github.bordertech.corpdir.jpa.modify.common.svc.JpaBasicKeyIdWriteService;
import com.github.bordertech.corpdir.jpa.v1.mapper.UnitTypeMapper;
import com.github.bordertech.corpdir.modify.api.v1.UnitTypeWriteService;
import javax.inject.Singleton;

/**
 * Organization unit type JPA write (modifiable) service implementation.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public class UnitTypeWriteServiceImpl extends JpaBasicKeyIdWriteService<UnitType, UnitTypeEntity> implements UnitTypeWriteService {

	private static final UnitTypeMapper UNITTYPE_MAPPER = new UnitTypeMapper();

	@Override
	protected Class<UnitTypeEntity> getEntityClass() {
		return UnitTypeEntity.class;
	}

	@Override
	protected MapperApi<UnitType, UnitTypeEntity> getMapper() {
		return UNITTYPE_MAPPER;
	}
}

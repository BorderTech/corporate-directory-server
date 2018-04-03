package com.github.bordertech.corpdir.jpa.v1.api;

import com.github.bordertech.corpdir.api.v1.model.SystemCtrl;
import com.github.bordertech.corpdir.jpa.common.map.MapperApi;
import com.github.bordertech.corpdir.jpa.common.svc.JpaBasicIdService;
import com.github.bordertech.corpdir.jpa.entity.SystemCtrlEntity;
import com.github.bordertech.corpdir.jpa.v1.mapper.SystemCtrlMapper;

/**
 * Abstract system control service implementation.
 * 
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public abstract class SystemCtrlServiceImpl extends JpaBasicIdService<SystemCtrl, SystemCtrlEntity> {

	protected static final SystemCtrlMapper MAPPER = new SystemCtrlMapper();

	@Override
	protected Class<SystemCtrlEntity> getEntityClass() {
		return SystemCtrlEntity.class;
	}

	@Override
	protected MapperApi<SystemCtrl, SystemCtrlEntity> getMapper() {
		return MAPPER;
	}
}

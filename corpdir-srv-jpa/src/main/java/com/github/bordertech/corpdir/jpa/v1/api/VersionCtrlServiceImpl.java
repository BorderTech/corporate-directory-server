package com.github.bordertech.corpdir.jpa.v1.api;

import com.github.bordertech.corpdir.api.v1.model.VersionCtrl;
import com.github.bordertech.corpdir.jpa.common.map.MapperApi;
import com.github.bordertech.corpdir.jpa.common.svc.JpaBasicIdService;
import com.github.bordertech.corpdir.jpa.entity.VersionCtrlEntity;
import com.github.bordertech.corpdir.jpa.v1.mapper.VersionCtrlMapper;

/**
 * Abstract version control service implementation.
 * 
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public abstract class VersionCtrlServiceImpl extends JpaBasicIdService<VersionCtrl, VersionCtrlEntity> {

	protected static final VersionCtrlMapper MAPPER = new VersionCtrlMapper();

	@Override
	protected MapperApi<VersionCtrl, VersionCtrlEntity> getMapper() {
		return MAPPER;
	}

	@Override
	protected Class<VersionCtrlEntity> getEntityClass() {
		return VersionCtrlEntity.class;
	}
}

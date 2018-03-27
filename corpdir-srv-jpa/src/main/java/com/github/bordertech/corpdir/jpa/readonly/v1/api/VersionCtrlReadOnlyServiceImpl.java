package com.github.bordertech.corpdir.jpa.readonly.v1.api;

import com.github.bordertech.corpdir.api.v1.model.VersionCtrl;
import com.github.bordertech.corpdir.jpa.common.map.MapperApi;
import com.github.bordertech.corpdir.jpa.entity.VersionCtrlEntity;
import com.github.bordertech.corpdir.jpa.readonly.common.svc.JpaBasicIdReadOnlyService;
import com.github.bordertech.corpdir.jpa.v1.mapper.VersionCtrlMapper;
import com.github.bordertech.corpdir.readonly.api.v1.VersionCtrlReadOnlyService;
import javax.inject.Singleton;

/**
 * Version Control JPA read-only service implementation.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public class VersionCtrlReadOnlyServiceImpl extends JpaBasicIdReadOnlyService<VersionCtrl, VersionCtrlEntity> implements VersionCtrlReadOnlyService {

	private static final VersionCtrlMapper MAPPER = new VersionCtrlMapper();
	

	@Override
	protected MapperApi<VersionCtrl, VersionCtrlEntity> getMapper() {
		return MAPPER;
	}

	@Override
	protected Class<VersionCtrlEntity> getEntityClass() {
		return VersionCtrlEntity.class;
	}
    
}

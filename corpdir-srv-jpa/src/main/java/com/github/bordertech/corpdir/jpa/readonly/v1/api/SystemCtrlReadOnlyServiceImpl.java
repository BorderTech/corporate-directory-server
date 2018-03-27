package com.github.bordertech.corpdir.jpa.readonly.v1.api;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.SystemCtrl;
import com.github.bordertech.corpdir.jpa.common.map.MapperApi;
import com.github.bordertech.corpdir.jpa.entity.SystemCtrlEntity;
import com.github.bordertech.corpdir.jpa.entity.VersionCtrlEntity;
import com.github.bordertech.corpdir.jpa.readonly.common.svc.JpaBasicIdReadOnlyService;
import com.github.bordertech.corpdir.jpa.v1.mapper.SystemCtrlMapper;
import com.github.bordertech.corpdir.readonly.api.v1.SystemCtrlReadOnlyService;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

/**
 * System Control JPA read-only service implementation.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public class SystemCtrlReadOnlyServiceImpl extends JpaBasicIdReadOnlyService<SystemCtrl, SystemCtrlEntity> implements SystemCtrlReadOnlyService {

	private static final SystemCtrlMapper MAPPER = new SystemCtrlMapper();

	@Override
	public DataResponse<Long> getCurrentVersion() {
		EntityManager em = getEntityManager();
		try {
			VersionCtrlEntity vers = getSystemCtrlEntity().getCurrentVersion();
			if (vers == null) {
				throw new IllegalStateException("No current version configured.");
			}
			return new DataResponse<>(vers.getId());
		} finally {
			em.close();
		}
	}

	protected SystemCtrlEntity getSystemCtrlEntity() {
		SystemCtrlEntity ctrl = getEntityManager().find(SystemCtrlEntity.class, Long.valueOf("1"));
		if (ctrl == null) {
			throw new IllegalStateException("No System Control Record Available.");
		}
		return ctrl;
	}

	@Override
	protected Class<SystemCtrlEntity> getEntityClass() {
		return SystemCtrlEntity.class;
	}

	@Override
	protected MapperApi<SystemCtrl, SystemCtrlEntity> getMapper() {
		return MAPPER;
	}
}

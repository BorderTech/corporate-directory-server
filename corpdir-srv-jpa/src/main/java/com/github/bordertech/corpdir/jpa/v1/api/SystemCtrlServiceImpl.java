package com.github.bordertech.corpdir.jpa.v1.api;

import com.github.bordertech.corpdir.api.response.BasicResponse;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.SystemCtrl;
import com.github.bordertech.corpdir.jpa.common.map.MapperApi;
import com.github.bordertech.corpdir.jpa.common.svc.JpaBasicIdService;
import com.github.bordertech.corpdir.jpa.entity.SystemCtrlEntity;
import com.github.bordertech.corpdir.jpa.entity.VersionCtrlEntity;
import com.github.bordertech.corpdir.jpa.v1.mapper.SystemCtrlMapper;
import com.github.bordertech.corpdir.modify.api.v1.SystemCtrlWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.SystemCtrlReadOnlyService;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract system control service implementation.
 * 
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class SystemCtrlServiceImpl extends JpaBasicIdService<SystemCtrl, SystemCtrlEntity> implements SystemCtrlReadOnlyService, SystemCtrlWriteService {

	protected static final SystemCtrlMapper MAPPER = new SystemCtrlMapper();
	private static final Logger LOG = LoggerFactory.getLogger(SystemCtrlServiceImpl.class);

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
	public DataResponse<Long> setCurrentVersion(final Long versionId) {
		if (versionId == null) {
			throw new IllegalArgumentException("A version id value must be provided.");
		}
		EntityManager em = getEntityManager();
		try {
			// Check version exsits
			VersionCtrlEntity vers = getEntityManager().find(VersionCtrlEntity.class, versionId);
			if (vers == null) {
				throw new IllegalArgumentException("Version id [" + versionId + "] does not exist.");
			}
			// Update the System Control Record
			em.getTransaction().begin();
			SystemCtrlEntity ctrl = getSystemCtrlEntity();
			ctrl.setCurrentVersion(vers);
			em.merge(ctrl);
			em.getTransaction().commit();
			LOG.info("Current version is now [" + versionId + "].");
			return new DataResponse<>(versionId);
		} finally {
			em.close();
		}
	}

	@Override
	public BasicResponse delete(final String id) {
		throw new UnsupportedOperationException("Delete not supported.");
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

package com.github.bordertech.corpdir.jpa.v1.api;

import com.github.bordertech.corpdir.api.exception.NotFoundException;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.jpa.common.map.MapperApiVersion;
import com.github.bordertech.corpdir.jpa.common.svc.JpaBasicVersionTreeService;
import com.github.bordertech.corpdir.jpa.entity.OrgUnitEntity;
import com.github.bordertech.corpdir.jpa.entity.PositionEntity;
import com.github.bordertech.corpdir.jpa.entity.VersionCtrlEntity;
import com.github.bordertech.corpdir.jpa.entity.version.OrgUnitVersionEntity;
import com.github.bordertech.corpdir.jpa.entity.version.PositionVersionEntity;
import com.github.bordertech.corpdir.jpa.util.MapperUtil;
import com.github.bordertech.corpdir.jpa.v1.mapper.OrgUnitMapper;
import com.github.bordertech.corpdir.jpa.v1.mapper.PositionMapper;
import com.github.bordertech.corpdir.modify.api.v1.OrgUnitWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.OrgUnitReadOnlyService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Abstract organization unit service implementation.
 * 
 * @author Jonathan Austin
 * @since 1.0.0
 */public class OrgUnitServiceImpl extends JpaBasicVersionTreeService<OrgUnit, OrgUnitVersionEntity, OrgUnitEntity> implements OrgUnitReadOnlyService, OrgUnitWriteService {

	protected static final PositionMapper POSITION_MAPPER = new PositionMapper();
	protected static final OrgUnitMapper ORGUNIT_MAPPER = new OrgUnitMapper();

	@Override
	public DataResponse<List<Position>> getPositions(final String keyId) {
		return getPositions(getCurrentVersionId(), keyId);
	}

	@Override
	public DataResponse<List<Position>> getPositions(final Long versionId, final String keyId) {
		EntityManager em = getEntityManager();
		try {
			OrgUnitEntity entity = getEntity(em, keyId);
			OrgUnitVersionEntity links = entity.getVersion(versionId);
			List<Position> list;
			if (links == null) {
				list = new ArrayList<>();
			} else {
				list = POSITION_MAPPER.convertEntitiesToApis(em, links.getPositions(), versionId);
			}
			return new DataResponse<>(list);
		} finally {
			em.close();
		}
	}

	@Override
	public DataResponse<Position> getManagerPosition(final String keyId) {
		return getManagerPosition(getCurrentVersionId(), keyId);
	}

	@Override
	public DataResponse<Position> getManagerPosition(final Long versionId, final String keyId) {
		EntityManager em = getEntityManager();
		try {
			OrgUnitEntity entity = getEntity(em, keyId);
			OrgUnitVersionEntity links = entity.getVersion(versionId);
			if (links == null || links.getManagerPosition() == null) {
				throw new NotFoundException("Org Unit has no manager");
			}
			Position data = POSITION_MAPPER.convertEntityToApi(em, links.getManagerPosition(), versionId);
			return new DataResponse<>(data);
		} finally {
			em.close();
		}
	}

	@Override
	public DataResponse<OrgUnit> addPosition(final String keyId, final String positionKeyId) {
		return addPosition(getCurrentVersionId(), keyId, positionKeyId);
	}

	@Override
	public DataResponse<OrgUnit> removePosition(final String keyId, final String positionKeyId) {
		return removePosition(getCurrentVersionId(), keyId, positionKeyId);
	}

	@Override
	public DataResponse<OrgUnit> addPosition(final Long versionId, final String keyId, final String positionKeyId) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			// Get the org unit
			OrgUnitEntity orgUnit = getEntity(em, keyId);
			// Get the position
			PositionEntity position = getPositionEntity(em, positionKeyId);
			// Get Version
			VersionCtrlEntity ctrl = getVersionCtrl(em, versionId);
			// Remove thge position from its old org unit (if had one)
			PositionVersionEntity posLinks = position.getVersion(versionId);
			OrgUnitEntity oldOU = posLinks == null ? null : posLinks.getOrgUnit();
			if (oldOU != null) {
				oldOU.getOrCreateVersion(ctrl).removePosition(position);
			}
			// Add the position to the org unit
			orgUnit.getOrCreateVersion(ctrl).addPosition(position);
			em.getTransaction().commit();
			return buildResponse(em, orgUnit, versionId);
		} finally {
			em.close();
		}
	}

	@Override
	public DataResponse<OrgUnit> removePosition(final Long versionId, final String keyId, final String positionKeyId) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			// Get the org unit
			OrgUnitEntity orgUnit = getEntity(em, keyId);
			// Get the position
			PositionEntity position = getPositionEntity(em, positionKeyId);
			// Get Version
			VersionCtrlEntity ctrl = getVersionCtrl(em, versionId);
			// Remove the position from the org unit
			orgUnit.getOrCreateVersion(ctrl).removePosition(position);
			em.getTransaction().commit();
			return buildResponse(em, orgUnit, versionId);
		} finally {
			em.close();
		}
	}

	/**
	 * @param em the entity manager
	 * @param keyId the position key or API id
	 * @return the position entity
	 */
	protected PositionEntity getPositionEntity(final EntityManager em, final String keyId) {
		PositionEntity entity = MapperUtil.getEntityByKeyId(em, keyId, PositionEntity.class);
		if (entity == null) {
			throw new NotFoundException("Position [" + keyId + "] not found.");
		}
		return entity;
	}

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

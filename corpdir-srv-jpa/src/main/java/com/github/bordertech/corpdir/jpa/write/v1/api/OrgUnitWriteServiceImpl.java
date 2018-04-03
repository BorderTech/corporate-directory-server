package com.github.bordertech.corpdir.jpa.write.v1.api;

import com.github.bordertech.corpdir.api.exception.NotFoundException;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.jpa.entity.OrgUnitEntity;
import com.github.bordertech.corpdir.jpa.entity.PositionEntity;
import com.github.bordertech.corpdir.jpa.entity.VersionCtrlEntity;
import com.github.bordertech.corpdir.jpa.entity.version.PositionVersionEntity;
import com.github.bordertech.corpdir.jpa.util.MapperUtil;
import com.github.bordertech.corpdir.jpa.v1.api.OrgUniteServiceImpl;
import com.github.bordertech.corpdir.jpa.v1.mapper.OrgUnitMapper;
import com.github.bordertech.corpdir.modify.api.v1.OrgUnitWriteService;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

/**
 * Organization unit JPA write (modifiable) service implementation.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public class OrgUnitWriteServiceImpl extends OrgUniteServiceImpl implements OrgUnitWriteService {

	private static final OrgUnitMapper ORGUNIT_MAPPER = new OrgUnitMapper();

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
}

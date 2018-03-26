package com.github.bordertech.corpdir.jpa.write.v1.api;

import com.github.bordertech.corpdir.api.exception.NotFoundException;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.jpa.common.map.MapperApiVersion;
import com.github.bordertech.corpdir.jpa.entity.ContactEntity;
import com.github.bordertech.corpdir.jpa.entity.PositionEntity;
import com.github.bordertech.corpdir.jpa.entity.VersionCtrlEntity;
import com.github.bordertech.corpdir.jpa.entity.version.PositionVersionEntity;
import com.github.bordertech.corpdir.jpa.modify.common.svc.JpaBasicVersionTreeWriteService;
import com.github.bordertech.corpdir.jpa.util.MapperUtil;
import com.github.bordertech.corpdir.jpa.v1.mapper.PositionMapper;
import com.github.bordertech.corpdir.modify.api.v1.PositionWriteService;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

/**
 * Position JPA write (modifiable) service implementation.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public class PositionWriteServiceImpl extends JpaBasicVersionTreeWriteService<Position, PositionVersionEntity, PositionEntity> implements PositionWriteService {

	private static final PositionMapper POSITION_MAPPER = new PositionMapper();

	@Override
	public DataResponse<Position> addContact(final String keyId, final String contactKeyId) {
		return addContact(getCurrentVersionId(), keyId, contactKeyId);
	}

	@Override
	public DataResponse<Position> removeContact(final String keyId, final String contactKeyId) {
		return removeContact(getCurrentVersionId(), keyId, contactKeyId);
	}

	@Override
	public DataResponse<Position> addContact(final Long versionId, final String keyId, final String contactKeyId) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			// Get the position
			PositionEntity position = getEntity(em, keyId);
			// Get the contact
			ContactEntity contact = getContactEntity(em, contactKeyId);
			// Get Version
			VersionCtrlEntity ctrl = getVersionCtrl(em, versionId);
			// Add Contact to the Position
			position.getOrCreateVersion(ctrl).addContact(contact);
			em.getTransaction().commit();
			return buildResponse(em, position, versionId);
		} finally {
			em.close();
		}
	}

	@Override
	public DataResponse<Position> removeContact(final Long versionId, final String keyId, final String contactKeyId) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			// Get the position
			PositionEntity position = getEntity(em, keyId);
			// Get the contact
			ContactEntity contact = getContactEntity(em, contactKeyId);
			// Get Version
			VersionCtrlEntity ctrl = getVersionCtrl(em, versionId);
			// Remove Contact from the Position
			position.getOrCreateVersion(ctrl).removeContact(contact);
			em.getTransaction().commit();
			return buildResponse(em, position, versionId);
		} finally {
			em.close();
		}
	}

	protected ContactEntity getContactEntity(final EntityManager em, final String keyId) {
		ContactEntity entity = MapperUtil.getEntityByKeyId(em, keyId, ContactEntity.class);
		if (entity == null) {
			throw new NotFoundException("Contact [" + keyId + "] not found.");
		}
		return entity;
	}

	@Override
	protected Class<PositionEntity> getEntityClass() {
		return PositionEntity.class;
	}

	@Override
	protected MapperApiVersion<Position, PositionVersionEntity, PositionEntity> getMapper() {
		return POSITION_MAPPER;
	}
}

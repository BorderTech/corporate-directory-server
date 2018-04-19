package com.github.bordertech.corpdir.jpa.v1.api;

import com.github.bordertech.corpdir.api.exception.NotFoundException;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.Contact;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.jpa.common.map.MapperApiVersion;
import com.github.bordertech.corpdir.jpa.common.svc.JpaBasicVersionTreeService;
import com.github.bordertech.corpdir.jpa.entity.ContactEntity;
import com.github.bordertech.corpdir.jpa.entity.PositionEntity;
import com.github.bordertech.corpdir.jpa.entity.VersionCtrlEntity;
import com.github.bordertech.corpdir.jpa.entity.version.PositionVersionEntity;
import com.github.bordertech.corpdir.jpa.util.MapperUtil;
import com.github.bordertech.corpdir.jpa.v1.mapper.ContactMapper;
import com.github.bordertech.corpdir.jpa.v1.mapper.OrgUnitMapper;
import com.github.bordertech.corpdir.jpa.v1.mapper.PositionMapper;
import com.github.bordertech.corpdir.modify.api.v1.PositionWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.PositionReadOnlyService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Abstract position service implementation.
 * 
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class PositionServiceImpl extends JpaBasicVersionTreeService<Position, PositionVersionEntity, PositionEntity> implements PositionReadOnlyService, PositionWriteService {

	protected static final ContactMapper CONTACT_MAPPER = new ContactMapper();
	protected static final OrgUnitMapper ORGUNIT_MAPPER = new OrgUnitMapper();
	protected static final PositionMapper POSITION_MAPPER = new PositionMapper();

	@Override
	protected Class<PositionEntity> getEntityClass() {
		return PositionEntity.class;
	}

	@Override
	protected Class<PositionVersionEntity> getVersionEntityClass() {
		return PositionVersionEntity.class;
	}

	@Override
	protected MapperApiVersion<Position, PositionVersionEntity, PositionEntity> getMapper() {
		return POSITION_MAPPER;
	}
	
	@Override
	public DataResponse<List<OrgUnit>> getManages(final String keyId) {
		return getManages(getCurrentVersionId(), keyId);
	}

	@Override
	public DataResponse<List<Contact>> getContacts(final String keyId) {
		return getContacts(getCurrentVersionId(), keyId);
	}

	@Override
	public DataResponse<List<OrgUnit>> getManages(final Long versionId, final String keyId) {
		EntityManager em = getEntityManager();
		try {
			PositionEntity entity = getEntity(em, keyId);
			PositionVersionEntity links = entity.getVersion(versionId);
			List<OrgUnit> list;
			if (links == null) {
				list = new ArrayList<>();
			} else {
				list = ORGUNIT_MAPPER.convertEntitiesToApis(em, links.getManageOrgUnits(), versionId);
			}
			return new DataResponse<>(list);
		} finally {
			em.close();
		}
	}

	@Override
	public DataResponse<List<Contact>> getContacts(final Long versionId, final String keyId) {
		EntityManager em = getEntityManager();
		try {
			PositionEntity entity = getEntity(em, keyId);
			PositionVersionEntity links = entity.getVersion(versionId);
			List<Contact> list;
			if (links == null) {
				list = new ArrayList<>();
			} else {
				list = CONTACT_MAPPER.convertEntitiesToApis(em, links.getContacts(), versionId);
			}
			return new DataResponse<>(list);
		} finally {
			em.close();
		}
	}
	
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
}

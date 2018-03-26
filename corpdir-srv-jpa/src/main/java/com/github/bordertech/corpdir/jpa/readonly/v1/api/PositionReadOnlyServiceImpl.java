package com.github.bordertech.corpdir.jpa.readonly.v1.api;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.Contact;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.jpa.common.map.MapperApiVersion;
import com.github.bordertech.corpdir.jpa.entity.PositionEntity;
import com.github.bordertech.corpdir.jpa.entity.version.PositionVersionEntity;
import com.github.bordertech.corpdir.jpa.readonly.common.svc.JpaBasicVersionTreeReadOnlyService;
import com.github.bordertech.corpdir.jpa.v1.mapper.ContactMapper;
import com.github.bordertech.corpdir.jpa.v1.mapper.OrgUnitMapper;
import com.github.bordertech.corpdir.jpa.v1.mapper.PositionMapper;
import com.github.bordertech.corpdir.readonly.api.v1.PositionReadOnlyService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

/**
 * Position JPA read-only service implementation.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public class PositionReadOnlyServiceImpl extends JpaBasicVersionTreeReadOnlyService<Position, PositionVersionEntity, PositionEntity> implements PositionReadOnlyService {

	private static final ContactMapper CONTACT_MAPPER = new ContactMapper();
	private static final OrgUnitMapper ORGUNIT_MAPPER = new OrgUnitMapper();
	private static final PositionMapper POSITION_MAPPER = new PositionMapper();

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
}

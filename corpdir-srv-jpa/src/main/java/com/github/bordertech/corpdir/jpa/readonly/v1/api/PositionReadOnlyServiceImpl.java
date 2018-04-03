package com.github.bordertech.corpdir.jpa.readonly.v1.api;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.Contact;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.jpa.entity.PositionEntity;
import com.github.bordertech.corpdir.jpa.entity.version.PositionVersionEntity;
import com.github.bordertech.corpdir.jpa.v1.api.PositionServiceImpl;
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
public class PositionReadOnlyServiceImpl extends PositionServiceImpl implements PositionReadOnlyService {

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
}

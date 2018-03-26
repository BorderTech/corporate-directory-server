package com.github.bordertech.corpdir.jpa.readonly.v1.api;

import com.github.bordertech.corpdir.api.exception.NotFoundException;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.jpa.common.map.MapperApiVersion;
import com.github.bordertech.corpdir.jpa.entity.OrgUnitEntity;
import com.github.bordertech.corpdir.jpa.entity.version.OrgUnitVersionEntity;
import com.github.bordertech.corpdir.jpa.readonly.common.svc.JpaBasicVersionTreeReadOnlyService;
import com.github.bordertech.corpdir.jpa.v1.mapper.OrgUnitMapper;
import com.github.bordertech.corpdir.jpa.v1.mapper.PositionMapper;
import com.github.bordertech.corpdir.readonly.api.v1.OrgUnitReadOnlyService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

/**
 * Organization unit JPA read-only service implementation.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public class OrgUnitReadOnlyServiceImpl extends JpaBasicVersionTreeReadOnlyService<OrgUnit, OrgUnitVersionEntity, OrgUnitEntity> implements OrgUnitReadOnlyService {

	private static final PositionMapper POSITION_MAPPER = new PositionMapper();
	private static final OrgUnitMapper ORGUNIT_MAPPER = new OrgUnitMapper();

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

package com.github.bordertech.corpdir.jpa.readonly.v1.api;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.api.v1.model.UnitType;
import com.github.bordertech.corpdir.jpa.common.map.MapperApi;
import com.github.bordertech.corpdir.jpa.entity.OrgUnitEntity;
import com.github.bordertech.corpdir.jpa.entity.UnitTypeEntity;
import com.github.bordertech.corpdir.jpa.readonly.common.svc.JpaBasicKeyIdReadOnlyService;
import com.github.bordertech.corpdir.jpa.util.CriteriaUtil;
import com.github.bordertech.corpdir.jpa.v1.mapper.OrgUnitMapper;
import com.github.bordertech.corpdir.jpa.v1.mapper.UnitTypeMapper;
import com.github.bordertech.corpdir.readonly.api.v1.UnitTypeReadOnlyService;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Organization unit type JPA read-only service implementation.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public class UnitTypeReadOnlyServiceImpl extends JpaBasicKeyIdReadOnlyService<UnitType, UnitTypeEntity> implements UnitTypeReadOnlyService {

	private static final OrgUnitMapper ORGUNIT_MAPPER = new OrgUnitMapper();
	private static final UnitTypeMapper UNITTYPE_MAPPER = new UnitTypeMapper();

	@Override
	public DataResponse<List<OrgUnit>> getOrgUnits(final String keyId) {
		EntityManager em = getEntityManager();
		try {
			UnitTypeEntity type = getEntity(em, keyId);
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<OrgUnitEntity> qry = cb.createQuery(OrgUnitEntity.class);
			Root<OrgUnitEntity> from = qry.from(OrgUnitEntity.class);
			qry.select(from);
			qry.where(cb.equal(from.get("type"), type));

			// Order by
			qry.orderBy(CriteriaUtil.getDefaultOrderBy(cb, from));

			List<OrgUnitEntity> rows = em.createQuery(qry).getResultList();
			List<OrgUnit> list = ORGUNIT_MAPPER.convertEntitiesToApis(em, rows, getCurrentVersionId());
			return new DataResponse<>(list);
		} finally {
			em.close();
		}
	}

	@Override
	protected Class<UnitTypeEntity> getEntityClass() {
		return UnitTypeEntity.class;
	}

	@Override
	protected MapperApi<UnitType, UnitTypeEntity> getMapper() {
		return UNITTYPE_MAPPER;
	}
}

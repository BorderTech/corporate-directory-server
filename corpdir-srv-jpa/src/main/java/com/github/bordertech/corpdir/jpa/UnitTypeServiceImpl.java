package com.github.bordertech.corpdir.jpa;

import com.github.bordertech.corpdir.api.UnitTypeService;
import com.github.bordertech.corpdir.api.data.OrgUnit;
import com.github.bordertech.corpdir.api.data.UnitType;
import com.github.bordertech.corpdir.api.response.ServiceBasicResponse;
import com.github.bordertech.corpdir.api.response.ServiceResponse;
import com.github.bordertech.corpdir.jpa.entity.OrgUnitEntity;
import com.github.bordertech.corpdir.jpa.entity.OrgUnitEntity_;
import com.github.bordertech.corpdir.jpa.entity.UnitTypeEntity;
import com.github.bordertech.corpdir.jpa.mapper.MapperUtil;
import com.github.bordertech.corpdir.jpa.mapper.OrgUnitMapper;
import com.github.bordertech.corpdir.jpa.mapper.UnitTypeMapper;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Organization unit type JPA service implementation.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
@Singleton
public class UnitTypeServiceImpl extends AbstractJpaService implements UnitTypeService {

	@Override
	public ServiceResponse<List<UnitType>> getUnitTypes() {
		EntityManager em = getEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<UnitTypeEntity> qry = cb.createQuery(UnitTypeEntity.class);

			Root<UnitTypeEntity> from = qry.from(UnitTypeEntity.class);
			qry.select(from);

			List<UnitTypeEntity> rows = em.createQuery(qry).getResultList();
			List<UnitType> data = UnitTypeMapper.convertEntitiesToApis(rows);
			return new ServiceResponse<>(data);
		} finally {
			em.close();
		}
	}

	@Override
	public ServiceResponse<List<OrgUnit>> getOrgUnits(final String typeKeyId) {
		EntityManager em = getEntityManager();
		try {
			UnitTypeEntity type = getUnitTypeEntity(em, typeKeyId);
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<OrgUnitEntity> qry = cb.createQuery(OrgUnitEntity.class);
			Root<OrgUnitEntity> from = qry.from(OrgUnitEntity.class);
			qry.select(from);
			qry.where(cb.equal(from.get(OrgUnitEntity_.type), type));

			List<OrgUnitEntity> rows = em.createQuery(qry).getResultList();
			List<OrgUnit> data = OrgUnitMapper.convertEntitiesToApis(rows);
			return new ServiceResponse<>(data);
		} finally {
			em.close();
		}
	}

	@Override
	public ServiceResponse<UnitType> getUnitType(final String typeKeyId) {
		EntityManager em = getEntityManager();
		try {
			UnitTypeEntity entity = getUnitTypeEntity(em, typeKeyId);
			UnitType data = UnitTypeMapper.convertEntityToApi(entity);
			return new ServiceResponse<>(data);
		} finally {
			em.close();
		}
	}

	@Override
	public ServiceResponse<String> createUnitType(final UnitType type) {
		EntityManager em = getEntityManager();
		try {
			type.setId(null);
			UnitTypeEntity entity = UnitTypeMapper.convertApiToEntity(type);
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			String apiId = MapperUtil.convertEntityIdforApi(entity.getId());
			return new ServiceResponse<>(apiId);
		} finally {
			em.close();
		}
	}

	@Override
	public ServiceResponse<UnitType> updateUnitType(final String typeKeyId, final UnitType type) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			UnitTypeEntity entity = UnitTypeMapper.convertApiToEntity(type);
			MapperUtil.checkIdentifiersMatch(type, entity);
			UnitTypeMapper.copyApiToEntity(type, entity);
			em.merge(entity);
			em.getTransaction().commit();
			UnitType data = UnitTypeMapper.convertEntityToApi(entity);
			return new ServiceResponse<>(data);
		} finally {
			em.close();
		}
	}

	@Override
	public ServiceBasicResponse deleteUnitType(final String typeKeyId) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			UnitTypeEntity entity = getUnitTypeEntity(em, typeKeyId);
			em.remove(entity);
			em.getTransaction().commit();
			return new ServiceBasicResponse();
		} finally {
			em.close();
		}
	}

	/**
	 * @param em the entity manager
	 * @param keyId the unit type key or API id
	 * @return the unit type entity
	 */
	private UnitTypeEntity getUnitTypeEntity(final EntityManager em, final String keyId) {
		return MapperUtil.getEntity(em, keyId, UnitTypeEntity.class);
	}
}

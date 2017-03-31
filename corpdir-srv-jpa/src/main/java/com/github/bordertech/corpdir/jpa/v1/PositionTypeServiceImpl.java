package com.github.bordertech.corpdir.jpa.v1;

import com.github.bordertech.corpdir.api.exception.NotFoundException;
import com.github.bordertech.corpdir.api.exception.ServiceException;
import com.github.bordertech.corpdir.api.response.ServiceBasicResponse;
import com.github.bordertech.corpdir.api.response.ServiceResponse;
import com.github.bordertech.corpdir.api.v1.PositionTypeService;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.api.v1.model.PositionType;
import com.github.bordertech.corpdir.jpa.common.AbstractJpaService;
import com.github.bordertech.corpdir.jpa.common.MapperUtil;
import com.github.bordertech.corpdir.jpa.v1.entity.PositionEntity;
import com.github.bordertech.corpdir.jpa.v1.entity.PositionTypeEntity;
import com.github.bordertech.corpdir.jpa.v1.mapper.PositionMapper;
import com.github.bordertech.corpdir.jpa.v1.mapper.PositionTypeMapper;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Position type JPA service implementation.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
@Singleton
public class PositionTypeServiceImpl extends AbstractJpaService implements PositionTypeService {

	private static final PositionTypeMapper POSITIONTYPE_MAPPER = new PositionTypeMapper();
	private static final PositionMapper POSITION_MAPPER = new PositionMapper();

	@Override
	public ServiceResponse<List<PositionType>> getPositionTypes(final String search) {
		EntityManager em = getEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<PositionTypeEntity> qry = cb.createQuery(PositionTypeEntity.class);

			// TODO Implement Search criteria
			Root<PositionTypeEntity> from = qry.from(PositionTypeEntity.class);
			qry.select(from);

			List<PositionTypeEntity> rows = em.createQuery(qry).getResultList();
			List<PositionType> list = POSITIONTYPE_MAPPER.convertEntitiesToApis(em, rows);
			return new ServiceResponse<>(list);
		} finally {
			em.close();
		}
	}

	@Override
	public ServiceResponse<PositionType> getPositionType(final String typeKeyId) {
		EntityManager em = getEntityManager();
		try {
			PositionTypeEntity entity = getPositionTypeEntity(em, typeKeyId);
			return buildResponse(em, entity);
		} finally {
			em.close();
		}
	}

	@Override
	public ServiceResponse<PositionType> createPositionType(final PositionType type) {
		EntityManager em = getEntityManager();
		try {
			MapperUtil.checkApiIDsForCreate(type);
			// Check business key does not exist
			PositionTypeEntity other = MapperUtil.getEntity(em, type.getBusinessKey(), PositionTypeEntity.class);
			if (other != null) {
				throw new ServiceException("A position type already exists with business key [" + type.getBusinessKey() + "].");
			}
			PositionTypeEntity entity = POSITIONTYPE_MAPPER.convertApiToEntity(em, type);
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			return buildResponse(em, entity);
		} finally {
			em.close();
		}
	}

	@Override
	public ServiceResponse<PositionType> updatePositionType(final String typeKeyId, final PositionType type) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			PositionTypeEntity entity = getPositionTypeEntity(em, typeKeyId);
			MapperUtil.checkIdentifiersMatch(type, entity);
			POSITIONTYPE_MAPPER.copyApiToEntity(em, type, entity);
			em.getTransaction().commit();
			return buildResponse(em, entity);
		} finally {
			em.close();
		}
	}

	@Override
	public ServiceBasicResponse deletePositionType(final String typeKeyId) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			PositionTypeEntity entity = getPositionTypeEntity(em, typeKeyId);
			em.remove(entity);
			em.getTransaction().commit();
			return new ServiceBasicResponse();
		} finally {
			em.close();
		}
	}

	@Override
	public ServiceResponse<List<Position>> getPositions(final String typeKeyId) {
		EntityManager em = getEntityManager();
		try {
			PositionTypeEntity type = getPositionTypeEntity(em, typeKeyId);
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<PositionEntity> qry = cb.createQuery(PositionEntity.class);
			Root<PositionEntity> from = qry.from(PositionEntity.class);
			qry.select(from);
			qry.where(cb.equal(from.get("type"), type));

			List<PositionEntity> rows = em.createQuery(qry).getResultList();
			List<Position> list = POSITION_MAPPER.convertEntitiesToApis(em, rows);
			return new ServiceResponse<>(list);
		} finally {
			em.close();
		}
	}

	/**
	 *
	 * @param em the entity manager
	 * @param entity the position type entity
	 * @return the service response with position type API object
	 */
	protected ServiceResponse<PositionType> buildResponse(final EntityManager em, final PositionTypeEntity entity) {
		PositionType data = POSITIONTYPE_MAPPER.convertEntityToApi(em, entity);
		return new ServiceResponse<>(data);
	}

	/**
	 * @param em the entity manager
	 * @param keyId the unit type key or API id
	 * @return the unit type entity
	 */
	protected PositionTypeEntity getPositionTypeEntity(final EntityManager em, final String keyId) {
		PositionTypeEntity entity = MapperUtil.getEntity(em, keyId, PositionTypeEntity.class);
		if (entity == null) {
			throw new NotFoundException("Position Type [" + keyId + "] not found.");
		}
		return entity;
	}

}

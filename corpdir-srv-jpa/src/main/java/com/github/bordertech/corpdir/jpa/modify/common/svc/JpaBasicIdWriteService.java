package com.github.bordertech.corpdir.jpa.modify.common.svc;

import com.github.bordertech.corpdir.api.common.ApiIdObject;
import com.github.bordertech.corpdir.api.response.BasicResponse;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.modify.BasicIdWriteService;
import com.github.bordertech.corpdir.jpa.common.feature.PersistIdObject;
import com.github.bordertech.corpdir.jpa.common.map.MapperApi;
import com.github.bordertech.corpdir.jpa.common.svc.AbstractJpaIdService;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

/**
 * Keyed Entity JPA write (modifiable) service implementation.
 *
 * @param <A> the API object type
 * @param <P> the entity type
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public abstract class JpaBasicIdWriteService<A extends ApiIdObject, P extends PersistIdObject> extends AbstractJpaIdService<A, P> implements BasicIdWriteService<A> {

	@Override
	public DataResponse<A> create(final A apiObject) {
		EntityManager em = getEntityManager();
		try {
			handleCreateVerify(em, apiObject);
			P entity = getMapper().convertApiToEntity(em, apiObject);
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			return buildResponse(em, entity);
		} finally {
			em.close();
		}
	}

	@Override
	public DataResponse<A> update(final String id, final A apiObject) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			P entity = getEntity(em, id);
			handleUpdateVerify(em, apiObject, entity);
			getMapper().copyApiToEntity(em, apiObject, entity);
			em.merge(entity);
			em.getTransaction().commit();
			return buildResponse(em, entity);
		} finally {
			em.close();
		}
	}

	@Override
	public BasicResponse delete(final String id) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			P entity = getEntity(em, id);
			handleDeleteVerify(em, entity);
			em.remove(entity);
			em.getTransaction().commit();
			return new BasicResponse();
		} finally {
			em.close();
		}
	}

	/**
	 *
	 * @param em the entity manager
	 * @param entity the entity
	 * @return the service response with API object
	 */
	protected DataResponse<A> buildResponse(final EntityManager em, final P entity) {
		A data = getMapper().convertEntityToApi(em, entity);
		return new DataResponse<>(data);
	}

	protected abstract MapperApi<A, P> getMapper();
}

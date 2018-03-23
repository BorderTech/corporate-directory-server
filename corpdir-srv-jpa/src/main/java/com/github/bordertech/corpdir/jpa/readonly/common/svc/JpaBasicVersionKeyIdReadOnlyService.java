package com.github.bordertech.corpdir.jpa.readonly.common.svc;

import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.readonly.BasicVersionKeyIdReadOnlyService;
import com.github.bordertech.corpdir.jpa.common.feature.PersistVersionableKeyId;
import com.github.bordertech.corpdir.jpa.common.map.MapperApiVersion;
import com.github.bordertech.corpdir.jpa.common.svc.AbstractJpaKeyIdService;
import com.github.bordertech.corpdir.jpa.common.version.ItemVersion;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Keyed Entity JPA read-only service implementation.
 *
 * @param <A> the API object type
 * @param <U> the versionable data
 * @param <P> the entity type
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public abstract class JpaBasicVersionKeyIdReadOnlyService<A extends ApiVersionable, U extends ItemVersion<P>, P extends PersistVersionableKeyId<P, U>> extends AbstractJpaKeyIdService<A, P> implements BasicVersionKeyIdReadOnlyService<A> {

	@Override
	public DataResponse<List<A>> search(final String search) {
		return search(getCurrentVersionId(), search);
	}

	@Override
	public DataResponse<A> retrieve(final String keyId) {
		return retrieve(getCurrentVersionId(), keyId);
	}

	@Override
	public DataResponse<List<A>> search(final Long versionId, final String search) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery<P> qry = handleSearchCriteria(em, search);
			List<P> rows = em.createQuery(qry).getResultList();
			List<A> list = getMapper().convertEntitiesToApis(em, rows, versionId);
			return new DataResponse<>(list);
		} finally {
			em.close();
		}
	}

	@Override
	public DataResponse<A> retrieve(final Long versionId, final String keyId) {
		EntityManager em = getEntityManager();
		try {
			P entity = getEntity(em, keyId);
			return buildResponse(em, entity, versionId);
		} finally {
			em.close();
		}
	}

	/**
	 *
	 * @param em the entity manager
	 * @param entity the entity
	 * @param versionId the version id
	 * @return the service response with API object
	 */
	protected DataResponse<A> buildResponse(final EntityManager em, final P entity, final Long versionId) {
		A data = getMapper().convertEntityToApi(em, entity, versionId);
		return new DataResponse<>(data);
	}

	protected abstract MapperApiVersion<A, U, P> getMapper();

	protected abstract Class<U> getVersionEntityClass();
}

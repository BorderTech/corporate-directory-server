package com.github.bordertech.corpdir.jpa.readonly.common.svc;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.readonly.BasicTreeReadOnlyService;
import com.github.bordertech.corpdir.jpa.common.feature.PersistKeyIdTree;
import com.github.bordertech.corpdir.jpa.util.CriteriaUtil;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Tree Entity read-only service.
 *
 * @param <A> the API object type
 * @param <P> the entity type
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public abstract class JpaBasicTreeReadOnlyService<A extends ApiTreeable, P extends PersistKeyIdTree<P>> extends JpaBasicKeyIdReadOnlyService<A, P> implements BasicTreeReadOnlyService<A> {

	@Override
	public DataResponse<List<A>> getSubs(final String keyId) {
		EntityManager em = getEntityManager();
		try {
			P entity = getEntity(em, keyId);
			List<A> list = getMapper().convertEntitiesToApis(em, entity.getChildren());
			return new DataResponse<>(list);
		} finally {
			em.close();
		}
	}

	@Override
	public DataResponse<List<A>> getRootItems() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery<P> qry = handleRootSearchCriteria(em);
			List<P> rows = em.createQuery(qry).getResultList();
			List<A> list = getMapper().convertEntitiesToApis(em, rows);
			return new DataResponse<>(list);
		} finally {
			em.close();
		}
	}

	protected CriteriaQuery<P> handleRootSearchCriteria(final EntityManager em) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<P> qry = cb.createQuery(getEntityClass());

		// Search (has null parent)
		Root<P> from = qry.from(getEntityClass());
		qry.select(from);
		qry.where(cb.isNull(from.<P>get("parent")));

		// Order by
		qry.orderBy(CriteriaUtil.getDefaultOrderBy(cb, from));
		return qry;
	}
}

package com.github.bordertech.corpdir.jpa.common.svc;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicVersionTreeService;
import com.github.bordertech.corpdir.jpa.common.feature.PersistVersionableKeyId;
import com.github.bordertech.corpdir.jpa.common.version.ItemTreeVersion;
import com.github.bordertech.corpdir.jpa.entity.VersionCtrlEntity;
import com.github.bordertech.corpdir.jpa.util.CriteriaUtil;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Tree Entity service.
 *
 * @param <A> the API object type
 * @param <U> the versionable type
 * @param <P> the entity type
 * @author Jonathan Austin
 * @since 1.0.0
 */
@Singleton
public abstract class JpaBasicVersionTreeService<A extends ApiTreeable & ApiVersionable, U extends ItemTreeVersion<P, U>, P extends PersistVersionableKeyId<P, U>> extends JpaBasicVersionKeyIdService<A, U, P> implements BasicVersionTreeService<A> {

	@Override
	public DataResponse<List<A>> getRootItems() {
		return getRootItems(getCurrentVersionId());
	}

	@Override
	public DataResponse<List<A>> getSubs(final String keyId) {
		return getSubs(getCurrentVersionId(), keyId);
	}

	@Override
	public DataResponse<A> addSub(final String keyId, final String subKeyId) {
		return addSub(getCurrentVersionId(), keyId, subKeyId);
	}

	@Override
	public DataResponse<A> removeSub(final String keyId, final String subKeyId) {
		return removeSub(getCurrentVersionId(), keyId, subKeyId);
	}

	@Override
	public DataResponse<List<A>> getSubs(final Long versionId, final String keyId) {
		EntityManager em = getEntityManager();
		try {
			P entity = getEntity(em, keyId);
			ItemTreeVersion tree = entity.getVersion(versionId);
			List<A> list;
			if (tree == null) {
				list = Collections.emptyList();
			} else {
				list = getMapper().convertEntitiesToApis(em, tree.getChildrenItems(), versionId);
			}
			return new DataResponse<>(list);
		} finally {
			em.close();
		}
	}

	@Override
	public DataResponse<A> addSub(final Long versionId, final String keyId, final String subKeyId) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			// Get the entity
			P entity = getEntity(em, keyId);
			// Get the sub entity
			P subEntity = getEntity(em, subKeyId);
			// Cant add an entity to itself
			if (Objects.equals(entity, subEntity)) {
				throw new IllegalArgumentException("Cannot add an Entity as a child to itself");
			}
			// Get version
			VersionCtrlEntity ctrl = getVersionCtrl(em, versionId);
			// Remove subEntity from its OLD parent (if it had one)
			U tree = subEntity.getVersion(versionId);
			if (tree != null) {
				if (Objects.equals(tree.getParentItem(), subEntity)) {
					throw new IllegalArgumentException("A entity cannot be a child and parent of the same entity.");
				}
				P oldParent = tree.getParentItem();
				if (oldParent != null) {
					oldParent.getOrCreateVersion(ctrl).removeChildItem(subEntity);
				}
			}
			// Add Child to the new parent
			entity.getOrCreateVersion(ctrl).addChildItem(subEntity);
			em.merge(entity);
			em.getTransaction().commit();
			return buildResponse(em, entity, versionId);
		} finally {
			em.close();
		}
	}

	@Override
	public DataResponse<A> removeSub(final Long versionId, final String keyId, final String subKeyId) {
		EntityManager em = getEntityManager();
		try {
			if (Objects.equals(keyId, subKeyId)) {
				throw new IllegalArgumentException("Cannot remove an entity from itself");
			}
			em.getTransaction().begin();
			// Get the entity
			P entity = getEntity(em, keyId);
			// Get the sub entity
			P subEntity = getEntity(em, subKeyId);
			// Get the version
			VersionCtrlEntity ctrl = getVersionCtrl(em, versionId);
			// Remove the sub entity
			entity.getOrCreateVersion(ctrl).removeChildItem(subEntity);
			em.merge(entity);
			em.getTransaction().commit();
			return buildResponse(em, entity, versionId);
		} finally {
			em.close();
		}
	}

	@Override
	protected void handleUpdateVerify(final EntityManager em, final A api, final P entity) {
		super.handleUpdateVerify(em, api, entity);
		if (Objects.equals(api.getId(), api.getParentId())) {
			throw new IllegalArgumentException("Cannot have itself as a parent.");
		}
		if (api.getSubIds().contains(api.getId())) {
			throw new IllegalArgumentException("Cannot have itself as a child.");
		}
		if (api.getParentId() != null && api.getSubIds().contains(api.getParentId())) {
			throw new IllegalArgumentException("A entity cannot be a child and parent of the same entity.");
		}
	}

	@Override
	public DataResponse<List<A>> getRootItems(final Long versionId) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery<P> qry = handleRootSearchCriteria(em, versionId);
			List<P> rows = em.createQuery(qry).getResultList();
			List<A> list = getMapper().convertEntitiesToApis(em, rows, versionId);
			return new DataResponse<>(list);
		} finally {
			em.close();
		}
	}

	protected CriteriaQuery<P> handleRootSearchCriteria(final EntityManager em, final Long versionId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<P> qry = cb.createQuery(getEntityClass());

		// Search (has null parent on the version data)
		Root<P> from = qry.from(getEntityClass());

		// Has No Parent or has no version join
		Join<P, U> join = from.join("versions", JoinType.LEFT);
		Predicate p1 = cb.equal(join.get("versionKey").get("versionId"), versionId);
		Predicate p2 = cb.isNull(join.get("parentVersionItem"));
		qry.where(cb.and(p1, p2));
		qry.select(from);
		// Order by
		qry.orderBy(CriteriaUtil.getDefaultOrderBy(cb, from));
		return qry;
	}

}

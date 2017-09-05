package com.github.bordertech.corpdir.jpa.common.svc;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicVersionTreeService;
import com.github.bordertech.corpdir.jpa.common.feature.PersistVersionData;
import com.github.bordertech.corpdir.jpa.common.feature.PersistVersionableTree;
import com.github.bordertech.corpdir.jpa.entity.VersionCtrlEntity;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

/**
 * Tree Entity service.
 *
 * @param <A> the API object type
 * @param <P> the entity type
 * @author Jonathan Austin
 * @since 1.0.0
 */
@Singleton
public abstract class JpaBasicVersionTreeService<A extends ApiTreeable & ApiVersionable, U extends PersistVersionableTree<U, P>, P extends PersistVersionData<U>> extends JpaBasicVersionService<A, U, P> implements BasicVersionTreeService<A> {

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
			PersistVersionableTree tree = entity.getDataVersion(versionId);
			List<A> list;
			if (tree == null) {
				list = Collections.EMPTY_LIST;
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
			U tree = subEntity.getDataVersion(versionId);
			P oldParent = tree == null ? null : tree.getParentItem();
			if (oldParent != null) {
				oldParent.getOrCreateDataVersion(ctrl).removeChildItem(subEntity);
			}
			// Add Child to the new parent
			entity.getOrCreateDataVersion(ctrl).addChildItem(subEntity);
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
			entity.getOrCreateDataVersion(ctrl).removeChildItem(subEntity);
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
			throw new IllegalArgumentException("Cannot have itself as a parent OU.");
		}
		if (api.getSubIds().contains(api.getId())) {
			throw new IllegalArgumentException("Cannot have itself as a child OU.");
		}
	}

}

package com.github.bordertech.corpdir.jpa.modify.common.svc;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.modify.BasicVersionTreeWriteService;
import com.github.bordertech.corpdir.jpa.common.feature.PersistVersionableKeyId;
import com.github.bordertech.corpdir.jpa.common.version.ItemTreeVersion;
import com.github.bordertech.corpdir.jpa.entity.VersionCtrlEntity;
import java.util.Objects;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

/**
 * Tree Entity write (modifiable) service.
 *
 * @param <A> the API object type
 * @param <U> the versionable type
 * @param <P> the entity type
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public abstract class JpaBasicVersionTreeWriteService<A extends ApiTreeable & ApiVersionable, U extends ItemTreeVersion<P, U>, P extends PersistVersionableKeyId<P, U>> extends JpaBasicVersionKeyIdWriteService<A, U, P> implements BasicVersionTreeWriteService<A> {

	@Override
	public DataResponse<A> addSub(final String keyId, final String subKeyId) {
		return addSub(getCurrentVersionId(), keyId, subKeyId);
	}

	@Override
	public DataResponse<A> removeSub(final String keyId, final String subKeyId) {
		return removeSub(getCurrentVersionId(), keyId, subKeyId);
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
}

package com.github.bordertech.corpdir.jpa.modify.common.svc;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.modify.BasicTreeWriteService;
import com.github.bordertech.corpdir.jpa.common.feature.PersistKeyIdTree;
import java.util.Objects;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

/**
 * Tree Entity write (modifiable) service.
 *
 * @param <A> the API object type
 * @param <P> the entity type
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public abstract class JpaBasicTreeWriteService<A extends ApiTreeable, P extends PersistKeyIdTree<P>> extends JpaBasicKeyIdWriteService<A, P> implements BasicTreeWriteService<A> {

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
	public DataResponse<A> addSub(final String keyId, final String subKeyId) {
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
			if (Objects.equals(entity.getParent(), subEntity)) {
				throw new IllegalArgumentException("A entity cannot be a child and parent of the same entity.");
			}
			// Remove Entity from its OLD parent (if it had one)
			P oldParent = subEntity.getParent();
			if (oldParent != null) {
				oldParent.removeChild(subEntity);
			}
			// Add to the new parent
			entity.addChild(subEntity);
			em.merge(entity);
			em.getTransaction().commit();
			return buildResponse(em, entity);
		} finally {
			em.close();
		}
	}

	@Override
	public DataResponse<A> removeSub(final String keyId, final String subKeyId) {
		EntityManager em = getEntityManager();
		try {
			if (Objects.equals(keyId, subKeyId)) {
				throw new IllegalArgumentException("Cannot remove an entity from itself");
			}
			em.getTransaction().begin();
			// Get the entity
			P entity = getEntity(em, keyId);
			// Get the sub entity
			P subOrgUnit = getEntity(em, subKeyId);
			// Remove the entity
			entity.removeChild(subOrgUnit);
			em.merge(entity);
			em.getTransaction().commit();
			return buildResponse(em, entity);
		} finally {
			em.close();
		}
	}
}

package com.github.bordertech.corpdir.jpa.common.map;

import com.github.bordertech.corpdir.api.common.ApiKeyIdTreeObject;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.jpa.common.PersistentKeyIdTreeVersionObject;
import com.github.bordertech.corpdir.jpa.entity.OrgUnitEntity;
import com.github.bordertech.corpdir.jpa.util.MapperUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Map {@link OrgUnit} and {@link OrgUnitEntity}.
 *
 * @param <A> the nested API
 * @param <P> the tree entity
 * @author jonathan
 */
public abstract class AbstractMapperKeyIdTreeVersion<A extends ApiKeyIdTreeObject, P extends PersistentKeyIdTreeVersionObject> implements MapperApiEntity<A, P> {

	@Override
	public P convertApiToEntity(final EntityManager em, final A from) {
		if (from == null) {
			return null;
		}
		Long id = MapperUtil.convertApiIdforEntity(from.getId());
		P to = createEntityObject(id);
		copyApiToEntity(em, from, to);
		return to;
	}

	@Override
	public void copyApiToEntity(final EntityManager em, final A from, final P to) {
		handleKeyIdApiToEntity(em, from, to);
	}

	@Override
	public A convertEntityToApi(final EntityManager em, final P from) {
		if (from == null) {
			return null;
		}
		A to = createApiObject();
		copyEntityToApi(em, from, to);
		return to;
	}

	@Override
	public void copyEntityToApi(final EntityManager em, final P from, final A to) {
		handleKeyIdEntityToApi(em, from, to);
	}

	@Override
	public List<A> convertEntitiesToApis(final EntityManager em, final Collection<P> rows) {
		if (rows == null || rows.isEmpty()) {
			return Collections.EMPTY_LIST;
		}

		List<A> items = new ArrayList<>();
		for (P row : rows) {
			items.add(convertEntityToApi(em, row));
		}
		return items;
	}

	@Override
	public List<P> convertApisToEntities(final EntityManager em, final Collection<A> rows) {
		if (rows == null || rows.isEmpty()) {
			return Collections.EMPTY_LIST;
		}

		List<P> items = new ArrayList<>();
		for (A row : rows) {
			items.add(convertApiToEntity(em, row));
		}
		return items;
	}

	public void handleKeyIdApiToEntity(final EntityManager em, final A from, final P to) {
		// Common KeyId fields
		to.setBusinessKey(from.getBusinessKey());
		to.setDescription(from.getDescription());
		to.setCustom(from.isCustom());
		to.setActive(from.isActive());
		to.setTimestamp(from.getTimestamp());
	}

	public void handleKeyIdEntityToApi(final EntityManager em, final P from, final A to) {
		// Common KeyId Fields
//		to.setId(convertEntityIdforApi(from));
		to.setBusinessKey(from.getBusinessKey());
		to.setDescription(from.getDescription());
		to.setCustom(from.isCustom());
		to.setActive(from.isActive());
		to.setTimestamp(from.getTimestamp());
	}

	abstract protected A createApiObject();

	abstract protected P createEntityObject(final Long id);

}

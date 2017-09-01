package com.github.bordertech.corpdir.jpa.common.map;

import com.github.bordertech.corpdir.api.common.ApiKeyIdObject;
import com.github.bordertech.corpdir.jpa.common.feature.PersistKeyIdObject;
import com.github.bordertech.corpdir.jpa.util.MapperUtil;
import static com.github.bordertech.corpdir.jpa.util.MapperUtil.convertEntityIdforApi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Map {@link ApiKeyIdObject} and {@link PersistKeyIdObject}.
 *
 * @param <A> the API keyed object
 * @param <P> the keyed persistent object
 * @author jonathan
 */
public abstract class AbstractMapperKeyId<A extends ApiKeyIdObject, P extends PersistKeyIdObject> implements MapperApi<A, P> {

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

	protected void handleKeyIdApiToEntity(final EntityManager em, final A from, final P to) {
		// Common KeyId fields
		to.setBusinessKey(from.getBusinessKey());
		to.setDescription(from.getDescription());
		to.setCustom(from.isCustom());
		to.setActive(from.isActive());
		to.setTimestamp(from.getTimestamp());
	}

	protected void handleKeyIdEntityToApi(final EntityManager em, final P from, final A to) {
		// Common KeyId Fields
		to.setId(convertEntityIdforApi(from));
		to.setBusinessKey(from.getBusinessKey());
		to.setDescription(from.getDescription());
		to.setCustom(from.isCustom());
		to.setActive(from.isActive());
		to.setTimestamp(from.getTimestamp());
	}

	protected abstract A createApiObject();

	protected abstract P createEntityObject(final Long id);

}

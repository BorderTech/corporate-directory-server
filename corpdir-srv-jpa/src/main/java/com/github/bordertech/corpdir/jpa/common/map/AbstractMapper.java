package com.github.bordertech.corpdir.jpa.common.map;

import com.github.bordertech.corpdir.api.common.ApiObject;
import com.github.bordertech.corpdir.jpa.common.feature.PersistObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Map {@link ApiObject} and {@link PersistObject}.
 *
 * @param <A> the API object
 * @param <P> the persistent object
 * @author jonathan
 */
public abstract class AbstractMapper<A extends ApiObject, P extends PersistObject> implements MapperApi<A, P> {

	@Override
	public P convertApiToEntity(final EntityManager em, final A from) {
		if (from == null) {
			return null;
		}
		P to = createEntityObject();
		copyApiToEntity(em, from, to);
		return to;
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
	public List<A> convertEntitiesToApis(final EntityManager em, final Collection<P> rows) {
		List<A> items = new ArrayList<>();
		if (rows != null) {
			for (P row : rows) {
				items.add(convertEntityToApi(em, row));
			}
		}
		return items;
	}

	@Override
	public List<P> convertApisToEntities(final EntityManager em, final Collection<A> rows) {
		List<P> items = new ArrayList<>();
		if (rows != null) {
			for (A row : rows) {
				items.add(convertApiToEntity(em, row));
			}
		}
		return items;
	}

	protected abstract A createApiObject();

	protected abstract P createEntityObject();

}

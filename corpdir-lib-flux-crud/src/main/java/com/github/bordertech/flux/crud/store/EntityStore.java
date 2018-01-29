package com.github.bordertech.flux.crud.store;

/**
 * Store that holds entity values.
 *
 * @author jonathan
 * @param <T> the entity type
 */
public interface EntityStore<T> extends RetrieveActionStore {

	T fetch(final T entity) throws RetrieveActionException;

	boolean isFetchDone(final T entity);
}

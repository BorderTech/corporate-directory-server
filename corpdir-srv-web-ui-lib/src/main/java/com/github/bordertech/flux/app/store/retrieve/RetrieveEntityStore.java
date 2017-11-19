package com.github.bordertech.flux.app.store.retrieve;

import com.github.bordertech.taskmanager.service.ServiceStatus;

/**
 * Store that holds entity values.
 *
 * @author jonathan
 * @param <T> the entity type
 */
public interface RetrieveEntityStore<T> extends RetrieveStore {

	T fetch(final T entity);

	ServiceStatus getFetchStatus(final T entity);

	boolean isFetchDone(final T entity);
}

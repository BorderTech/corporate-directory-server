package com.github.bordertech.flux.crud.dataapi;

import com.github.bordertech.flux.dataapi.SearchApi;

/**
 * CRUD Entity API.
 *
 * @param <S> the search criteria
 * @param <K> the item key type
 * @param <T> the item type
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public interface CrudApi<S, K, T> extends SearchApi<S, K, T> {

	T retrieve(final K key);

	T create(final T item);

	T update(final T item);

	void delete(final T item);

	T createInstance();

}

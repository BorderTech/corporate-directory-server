package com.github.bordertech.corpdir.sync.common;

/**
 * Synchronisation service for keyed API object that have version data.
 * 
 * @param <S> source service to load data from
 * @param <D> destination service to write data to
 * @param <A> the keyed API version object
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public interface ApiVersionKeyIdSynchronisation<S, D, A> extends ApiKeyIdSynchronisation<S, D, A> {

	void syncBaseData(final Long version);

	void syncLinkedData(final Long version);

}

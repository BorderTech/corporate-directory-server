package com.github.bordertech.corpdir.sync.common;

import com.github.bordertech.corpdir.api.response.DataResponse;
import java.util.List;

/**
 * Synchronisation service for keyed API object.
 * 
 * @param <S> source service to load data from
 * @param <D> destination service to write data to
 * @param <A> the keyed API object
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public interface DataSynchronisation<S, D, A> {

	S getSourceService();

	D getDestinationService();

	void syncBaseData();

	DataResponse<List<A>> getSourceData();

}

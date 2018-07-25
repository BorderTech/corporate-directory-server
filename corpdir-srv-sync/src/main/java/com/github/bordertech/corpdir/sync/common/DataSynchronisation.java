package com.github.bordertech.corpdir.sync.common;

import com.github.bordertech.corpdir.api.response.DataResponse;
import java.util.List;

/**
 *
 * @author exiqaj
 * @param <S>
 * @param <D>
 * @param <A>
 */
public interface DataSynchronisation<S, D, A> {

	S getSourceService();

	D getDestinationService();

	void syncBaseData();

	DataResponse<List<A>> getSourceData();

}

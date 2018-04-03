package com.github.bordertech.corpdir.web.ui.flux.dataapi;

import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.modify.service.BasicVersionKeyIdWriteService;
import com.github.bordertech.corpdir.api.readonly.service.BasicVersionKeyIdReadOnlyService;
import java.util.List;

/**
 * Corp CRUD Version API with defined types.
 *
 * @param <T> the Corp API Versionable Object
 * @param <R> the Corp backing read-only Service
 * @param <W> the Corp backing write Service
 * 
 * @author Jonathan Austin
 * @author Aswin Kandula
 */
public interface CorpCrudVersionDataApi<T extends ApiVersionable, R extends BasicVersionKeyIdReadOnlyService<T>, W extends BasicVersionKeyIdWriteService<T>> extends CorpCrudDataApi<T, R, W> {

	T retrieve(final Long versionId, final String key);

	List<T> search(final Long versionId, final String criteria);
}

package com.github.bordertech.corpdir.web.ui.flux.dataapi.temp;

import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.modify.BasicVersionKeyIdWriteService;
import com.github.bordertech.corpdir.api.service.readonly.BasicVersionKeyIdReadOnlyService;
import java.util.List;

/**
 * CRUD Tree API calling CorpDir Services.
 *
 * @param <T> the CorpDir Treeable API object
 * @param <R> the CorpDir tree read-only service type
 * @param <W> the CorpDir tree write service type
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 */
public class DefaultCorpCrudVersionDataApiTemp<T extends ApiVersionable, R extends BasicVersionKeyIdReadOnlyService<T>, W extends BasicVersionKeyIdWriteService<T>> extends DefaultCorpCrudDataApiTemp<T, R, W> implements CorpCrudVersionDataApiTemp<T, R, W> {

	public DefaultCorpCrudVersionDataApiTemp(final Class<T> apiClass, final R readService, final W writeService) {
		super(apiClass, readService, writeService);
	}

	@Override
	public T retrieve(final Long versionId, final String key) {
		DataResponse<T> resp = getReadService().retrieve(versionId, key);
		return resp.getData();
	}

	@Override
	public List<T> search(final Long versionId, final String criteria) {
		DataResponse<List<T>> resp = getReadService().search(versionId, criteria);
		return resp.getData();
	}
}

package com.github.bordertech.corpdir.web.ui.flux.dataapi;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.modify.BasicVersionTreeWriteService;
import com.github.bordertech.corpdir.api.service.readonly.BasicVersionTreeReadOnlyService;
import java.util.List;

/**
 * CRUD Tree Version API calling CorpDir Services.
 *
 * @param <T> the treeable API type
 * @param <R> the versionable tree read-only service type
 * @param <W> the versionable tree write service type
 * 
 * @author Jonathan Austin
 * @author Aswin Kandula
 */
public class DefaultCorpCrudTreeVersionDataApi<T extends ApiTreeable & ApiVersionable, R extends BasicVersionTreeReadOnlyService<T>, W extends BasicVersionTreeWriteService<T>> extends DefaultCorpCrudTreeDataApi<T, R, W> implements CorpCrudTreeVersionDataApi<T, R, W> {

	public DefaultCorpCrudTreeVersionDataApi(final Class<T> apiClass, final R readService, final W writeService) {
		super(apiClass, readService, writeService);
	}

	@Override
	public boolean hasChildren(final Long versionId, final T item) {
		return !item.getSubIds().isEmpty();
	}

	@Override
	public List<T> getChildren(final Long versionId, final T item) {
		DataResponse<List<T>> resp = getReadService().getSubs(versionId, item.getId());
		return resp.getData();
	}

	@Override
	public List<T> getRootItems(final Long versionId) {
		DataResponse<List<T>> resp = getReadService().getRootItems(versionId);
		return resp.getData();
	}

	@Override
	public T addChild(final Long versionId, final T parent, final T child) {
		DataResponse<T> resp = getWriteService().addSub(versionId, parent.getId(), child.getId());
		return resp.getData();
	}

	@Override
	public T removeChild(final Long versionId, final T parent, final T child) {
		DataResponse<T> resp = getWriteService().removeSub(versionId, parent.getId(), child.getId());
		return resp.getData();
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

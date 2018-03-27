package com.github.bordertech.corpdir.web.ui.flux.dataapi;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.modify.BasicTreeWriteService;
import com.github.bordertech.corpdir.api.service.readonly.BasicTreeReadOnlyService;
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
public class DefaultCorpCrudTreeDataApi<T extends ApiTreeable, R extends BasicTreeReadOnlyService<T>, W extends BasicTreeWriteService<T>> extends DefaultCorpCrudDataApi<T, R, W> implements CorpCrudTreeDataApi<T, R, W> {

	public DefaultCorpCrudTreeDataApi(final Class<T> apiClass, final R readService, final W writeService) {
		super(apiClass, readService, writeService);
	}

	@Override
	public List<T> search(final String criteria) {
		DataResponse<List<T>> resp = getReadService().search(criteria);
		return resp.getData();
	}

	@Override
	public List<T> getChildren(final T entity) {
		DataResponse<List<T>> resp = getReadService().getSubs(entity.getId());
		return resp.getData();
	}

	@Override
	public List<T> getRootItems() {
		DataResponse<List<T>> resp = getReadService().getRootItems();
		return resp.getData();
	}

	@Override
	public T addChild(final T parent, final T child) {
		DataResponse<T> resp = getWriteService().addSub(parent.getId(), child.getId());
		return resp.getData();
	}

	@Override
	public T removeChild(final T parent, final T child) {
		DataResponse<T> resp = getWriteService().removeSub(parent.getId(), child.getId());
		return resp.getData();
	}

	@Override
	public boolean hasChildren(final T entity) {
		return !entity.getSubIds().isEmpty();
	}

	@Override
	public String getItemLabel(final T entity) {
		return entity.getDescription() + " [" + entity.getBusinessKey() + "]";
	}

	@Override
	public String getItemId(final T entity) {
		return entity.getId();
	}

}

package com.github.bordertech.corpdir.web.ui.flux.dataapi.temp;

import com.github.bordertech.corpdir.api.common.ApiIdObject;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.modify.BasicIdWriteService;
import com.github.bordertech.corpdir.api.service.readonly.BasicIdReadOnlyService;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * CRUD API calling CorpDir Services.
 *
 * @param <R> the CorpDir tree read-only service type
 * @param <W> the CorpDir tree write service type
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 */
public class DefaultCorpCrudDataApiTemp<T extends ApiIdObject, R extends BasicIdReadOnlyService<T>, W extends BasicIdWriteService<T>> implements CorpCrudDataApiTemp<T, R, W> {

	private final Class<T> apiClass;
	private final R readService;
	private final W writeService;

	public DefaultCorpCrudDataApiTemp(final Class<T> apiClass, final R readService, final W writeService) {
		this.apiClass = apiClass;
		this.readService = readService;
		this.writeService = writeService;
	}

	@Override
	public final R getReadService() {
		return readService;
	}

	@Override
	public final W getWriteService() {
		return writeService;
	}

	@Override
	public final Class<T> getApiClass() {
		return apiClass;
	}

	@Override
	public List<T> search(final String criteria) {
		DataResponse<List<T>> resp = getReadService().search(criteria);
		return resp.getData();
	}

	@Override
	public T create(final T entity) {
		DataResponse<T> resp = getWriteService().create(entity);
		return resp.getData();
	}

	@Override
	public T update(final T entity) {
		DataResponse<T> resp = getWriteService().update(entity.getId(), entity);
		return resp.getData();
	}

	@Override
	public void delete(final T entity) {
		getWriteService().delete(entity.getId());
	}

	@Override
	public T retrieve(final String key) {
		DataResponse<T> resp = getReadService().retrieve(key);
		return resp.getData();
	}

	@Override
	public String getItemKey(final T item) {
		return item.getId();
	}

	@Override
	public T createInstance() {
		try {
			return (T) getApiClass().getConstructor(String.class).newInstance((Object) null);
		} catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
			throw new IllegalStateException("Could not create API class. " + e.getMessage(), e);
		}
	}
}

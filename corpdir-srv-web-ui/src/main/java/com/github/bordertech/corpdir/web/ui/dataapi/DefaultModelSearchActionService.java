package com.github.bordertech.corpdir.web.ui.dataapi;

import com.github.bordertech.corpdir.api.common.ApiIdObject;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicIdService;
import com.github.bordertech.didums.Didums;
import com.github.bordertech.flux.crud.dataapi.CrudApi;
import com.github.bordertech.flux.crud.dataapi.SearchApi;
import java.util.List;

/**
 * API Search and Action Model.
 *
 * @param <T> the API object type
 * @param <B> the basic service type
 *
 * @author jonathan
 */
public class DefaultModelSearchActionService<T extends ApiIdObject, B extends BasicIdService<T>> implements SearchApi<String, T>, CrudApi<T> {

	private final Class<T> apiClass;
	private final B service;

	public DefaultModelSearchActionService(final Class<T> apiClass, final Class<? extends B> serviceClass) {
		this.apiClass = apiClass;
		this.service = Didums.getService(serviceClass);
	}

	protected final B getService() {
		return service;
	}

	@Override
	public List<T> search(final String criteria) {
		DataResponse<List<T>> resp = service.search(criteria);
		return resp.getData();
	}

	@Override
	public T create(final T entity) {
		DataResponse<T> resp = service.create(entity);
		return resp.getData();
	}

	@Override
	public T update(final T entity) {
		DataResponse<T> resp = service.update(entity.getId(), entity);
		return resp.getData();
	}

	@Override
	public void delete(final T entity) {
		service.delete(entity.getId());
	}

	@Override
	public T retrieve(final T entity) {
		DataResponse<T> resp = service.retrieve(entity.getId());
		return resp.getData();
	}

	@Override
	public T createInstance() {
		try {
			return (T) apiClass.getConstructor(String.class).newInstance((Object) null);
		} catch (Exception e) {
			throw new IllegalStateException("Could not create API class. " + e.getMessage(), e);
		}
	}

}

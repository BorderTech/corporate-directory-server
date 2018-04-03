package com.github.bordertech.corpdir.web.ui.flux.dataapi;

import com.github.bordertech.corpdir.api.common.ApiIdObject;
import com.github.bordertech.corpdir.api.modify.service.BasicIdWriteService;
import com.github.bordertech.corpdir.api.readonly.service.BasicIdReadOnlyService;
import com.github.bordertech.flux.crud.dataapi.CrudApi;

/**
 * Corp CRUD API with defined types.
 *
 * @param <T> the Corp API Object
 * @param <R> the Corp backing read-only Service
 * @param <W> the Corp backing write Service
 * 
 * @author Jonathan Austin
 * @author Aswin Kandula
 */
public interface CorpCrudDataApi<T extends ApiIdObject, R extends BasicIdReadOnlyService<T>, W extends BasicIdWriteService<T>> extends CrudApi<String, String, T> {

	Class<T> getApiClass();

	R getReadService();

	W getWriteService();
}

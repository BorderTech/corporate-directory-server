package com.github.bordertech.corpdir.web.ui.flux.store;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.flux.crud.store.DataApiCrudTreeStore;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.CorpCrudTreeDataApi;

/**
 * Corp CRUD Tree Store with backing data API.
 *
 * @param <T> the CorpDir API Object
 * @param <D> the CorpDir data API type
 * 
 * @author Jonathan Austin
 * @author Aswin Kandula
 */
public interface CorpCrudTreeStore<T extends ApiTreeable, D extends CorpCrudTreeDataApi<T, ?, ?>>
		extends CorpCrudStore<T, D>, DataApiCrudTreeStore<String, String, T, D> {
    
}

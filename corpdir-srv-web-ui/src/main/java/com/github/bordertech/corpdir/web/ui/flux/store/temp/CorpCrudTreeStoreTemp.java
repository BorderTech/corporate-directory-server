package com.github.bordertech.corpdir.web.ui.flux.store.temp;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.temp.CorpCrudTreeDataApiTemp;
import com.github.bordertech.flux.crud.store.DataApiCrudTreeStore;

/**
 * Corp CRUD Tree Store with backing data API.
 *
 * @param <T> the CorpDir API Object
 * @param <D> the CorpDir data API type
 * 
 * @author Jonathan Austin
 * @author Aswin Kandula
 */
public interface CorpCrudTreeStoreTemp<T extends ApiTreeable, D extends CorpCrudTreeDataApiTemp<T, ?, ?>>
		extends CorpCrudStoreTemp<T, D>, DataApiCrudTreeStore<String, String, T, D> {
    
}

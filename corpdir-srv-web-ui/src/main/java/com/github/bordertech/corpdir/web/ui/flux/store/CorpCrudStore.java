package com.github.bordertech.corpdir.web.ui.flux.store;

import com.github.bordertech.corpdir.api.common.ApiIdObject;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.temp.CorpCrudDataApiTemp;
import com.github.bordertech.flux.crud.store.DataApiCrudStore;

/**
 * Corp CRUD Store with backing data API.
 *
 * @param <T> the CorpDir API Object
 * @param <D> the CorpDir data API type
 * 
 * @author Jonathan Austin
 * @author Aswin Kandula
 */
public interface CorpCrudStore<T extends ApiIdObject, D extends CorpCrudDataApiTemp<T, ?, ?>> extends DataApiCrudStore<String, String, T, D> {
    
}

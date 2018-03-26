package com.github.bordertech.corpdir.web.ui.flux.store.temp;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.temp.CorpCrudTreeVersionDataApiTemp;

/**
 * Corp CRUD Tree Version Store with backing data API.
 *
 * @author jonathan
 * @param <T> the CorpDir API Object
 * @param <D> the CorpDir data API type
 * 
 * @author Jonathan Austin
 * @author Aswin Kandula
 */
public interface CorpCrudTreeVersionStoreTemp<T extends ApiTreeable & ApiVersionable, D extends CorpCrudTreeVersionDataApiTemp<T, ?, ?>>
		extends CorpCrudTreeStoreTemp<T, D>, CorpCrudVersionStoreTemp<T, D> {
    
}

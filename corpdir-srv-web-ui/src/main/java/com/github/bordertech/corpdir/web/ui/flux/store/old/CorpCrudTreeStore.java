package com.github.bordertech.corpdir.web.ui.flux.store.old;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.CorpCrudTreeDataApi;
import com.github.bordertech.flux.crud.store.DataApiCrudTreeStore;

/**
 * Corp CRUD Tree Store with backing data API.
 *
 * @author jonathan
 * @param <T> the CorpDir API Object
 * @param <D> the CorpDir data API type
 * @deprecated 
 */
@Deprecated
public interface CorpCrudTreeStore<T extends ApiTreeable, D extends CorpCrudTreeDataApi<T, ?>>
		extends CorpCrudStore<T, D>, DataApiCrudTreeStore<String, String, T, D> {
}

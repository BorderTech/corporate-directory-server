package com.github.bordertech.corpdir.web.ui.flux.store.old;

import com.github.bordertech.corpdir.api.common.ApiIdObject;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.old.CorpCrudDataApi;
import com.github.bordertech.flux.crud.store.DataApiCrudStore;

/**
 * Corp CRUD Store with backing data API.
 *
 * @author jonathan
 * @param <T> the CorpDir API Object
 * @param <D> the CorpDir data API type
 * @deprecated use {@link com.github.bordertech.corpdir.web.ui.flux.store.CorpCrudStore}
 */
@Deprecated
public interface CorpCrudStore<T extends ApiIdObject, D extends CorpCrudDataApi<T, ?>>
		extends DataApiCrudStore<String, String, T, D> {
}

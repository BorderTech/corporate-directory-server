package com.github.bordertech.corpdir.web.ui.flux.store.old;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.old.CorpCrudTreeVersionDataApi;

/**
 * Corp CRUD Tree Version Store with backing data API.
 *
 * @author jonathan
 * @param <T> the CorpDir API Object
 * @param <D> the CorpDir data API type
 * @deprecated 
 */
@Deprecated
public interface CorpCrudTreeVersionStore<T extends ApiTreeable & ApiVersionable, D extends CorpCrudTreeVersionDataApi<T, ?>>
		extends CorpCrudTreeStore<T, D>, CorpCrudVersionStore<T, D> {

}

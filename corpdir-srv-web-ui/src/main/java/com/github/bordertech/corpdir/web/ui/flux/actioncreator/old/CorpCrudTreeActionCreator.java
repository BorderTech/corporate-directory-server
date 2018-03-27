package com.github.bordertech.corpdir.web.ui.flux.actioncreator.old;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.old.CorpCrudTreeDataApi;
import com.github.bordertech.flux.crud.actioncreator.DataApiCrudTreeActionCreator;

/**
 * Corp CRUD Tree Action Creator with defined types.
 *
 * @param <T> the API type
 * @param <D> the backing data API
 * @author jonathan
 
 * @deprecated use {@link com.github.bordertech.corpdir.web.ui.flux.actioncreator.CorpCrudTreeActionCreator}
 */
@Deprecated
public interface CorpCrudTreeActionCreator<T extends ApiTreeable, D extends CorpCrudTreeDataApi<T, ?>> extends CorpCrudActionCreator<T, D>, DataApiCrudTreeActionCreator<String, T, D> {

}

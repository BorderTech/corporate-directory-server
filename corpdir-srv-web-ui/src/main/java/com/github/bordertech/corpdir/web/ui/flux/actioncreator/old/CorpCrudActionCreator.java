package com.github.bordertech.corpdir.web.ui.flux.actioncreator.old;

import com.github.bordertech.corpdir.api.common.ApiIdObject;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.old.CorpCrudDataApi;
import com.github.bordertech.flux.crud.actioncreator.DataApiCrudActionCreator;

/**
 * Corp CRUD Action Creator with defined types.
 *
 * @param <T> the API type
 * @param <D> the backing data API
 * @author jonathan
 * @deprecated use {@link com.github.bordertech.corpdir.web.ui.flux.actioncreator.CorpCrudActionCreator}
 */
@Deprecated
public interface CorpCrudActionCreator<T extends ApiIdObject, D extends CorpCrudDataApi<T, ?>> extends DataApiCrudActionCreator<String, T, D> {

}

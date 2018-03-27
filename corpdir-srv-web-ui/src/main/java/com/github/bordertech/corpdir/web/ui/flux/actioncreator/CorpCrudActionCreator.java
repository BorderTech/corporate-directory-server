package com.github.bordertech.corpdir.web.ui.flux.actioncreator;

import com.github.bordertech.corpdir.api.common.ApiIdObject;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.temp.CorpCrudDataApiTemp;
import com.github.bordertech.flux.crud.actioncreator.DataApiCrudActionCreator;

/**
 * Corp CRUD Action Creator with defined types.
 *
 * @param <T> the API type
 * @param <D> the backing data API
 * 
 * @author Jonathan Austin
 * @author Aswin Kandula
 */
public interface CorpCrudActionCreator<T extends ApiIdObject, D extends CorpCrudDataApiTemp<T, ?, ?>> extends DataApiCrudActionCreator<String, T, D> {
    
}

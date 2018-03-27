/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bordertech.corpdir.web.ui.flux.actioncreator;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.flux.crud.actioncreator.DataApiCrudTreeActionCreator;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.CorpCrudTreeDataApi;

/**
 * Corp CRUD Tree Action Creator with defined types.
 *
 * @param <T> the API type
 * @param <D> the backing data API
 * 
 * @author Jonathan Austin
 * @author Aswin Kandula
 */
public interface CorpCrudTreeActionCreator<T extends ApiTreeable, D extends CorpCrudTreeDataApi<T, ?, ?>> extends CorpCrudActionCreator<T, D>, DataApiCrudTreeActionCreator<String, T, D> {
    
}

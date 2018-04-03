package com.github.bordertech.corpdir.web.ui.flux.dataapi;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.modify.service.BasicTreeWriteService;
import com.github.bordertech.corpdir.api.readonly.service.BasicTreeReadOnlyService;
import com.github.bordertech.flux.crud.dataapi.CrudTreeApi;

/**
 * Corp CRUD Tree API with defined types.
 *
 * @param <T> the Corp API Treeable Object
 * @param <R> the Corp backing Tree read-only Service
 * @param <W> the Corp backing Tree write Service
 * 
 * @author Jonathan Austin
 * @author Aswin Kandula
 */
public interface CorpCrudTreeDataApi<T extends ApiTreeable, R extends BasicTreeReadOnlyService<T>, W extends BasicTreeWriteService<T>> extends CorpCrudDataApi<T, R, W>, CrudTreeApi<String, String, T> {
    
}

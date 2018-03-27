package com.github.bordertech.corpdir.web.ui.flux.store;

import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.temp.CorpCrudVersionDataApiTemp;

/**
 * Corp CRUD Store with backing data API.
 * 
 * @param <T> the Corp API Object
 * 
 * @param <D> the Corp data API type
 * @author Jonathan Austin
 * @author Aswin Kandula
 */
public interface CorpCrudVersionStore<T extends ApiVersionable, D extends CorpCrudVersionDataApiTemp<T, ?, ?>> extends CorpCrudStore<T, D> {

	/**
	 * @return the current version id
	 */
	Long getCurrentVersionId();
}

package com.github.bordertech.corpdir.web.ui.flux.actioncreator.temp;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.web.ui.CorpEntityType;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.temp.CorpCrudTreeDataApiTemp;
import com.github.bordertech.flux.crud.actioncreator.impl.DefaultDataApiCrudTreeActionCreator;

/**
 * Corp CRUD Tree Action Creator with defined types.
 *
 * @param <T> the Corp API object type
 * @param <D> the backing Corp API
 * 
 * @author Jonathan Austin
 * @author Aswin Kandula
 */
public class DefaultCorpCrudTreeActionCreatorTemp<T extends ApiTreeable, D extends CorpCrudTreeDataApiTemp<T, ?, ?>> extends DefaultDataApiCrudTreeActionCreator<String, T, D> implements CorpCrudTreeActionCreatorTemp<T, D> {

	/**
	 * @param type the entity type
	 * @param api the backing data API
	 */
	public DefaultCorpCrudTreeActionCreatorTemp(final CorpEntityType type, D api) {
		super(type.getActionCreatorKey(), api);
	}
}

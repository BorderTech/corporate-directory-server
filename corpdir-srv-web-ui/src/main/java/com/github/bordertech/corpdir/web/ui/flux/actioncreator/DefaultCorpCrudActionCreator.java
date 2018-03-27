package com.github.bordertech.corpdir.web.ui.flux.actioncreator;

import com.github.bordertech.corpdir.api.common.ApiIdObject;
import com.github.bordertech.corpdir.web.ui.CorpEntityType;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.temp.CorpCrudDataApiTemp;
import com.github.bordertech.flux.crud.actioncreator.impl.DefaultDataApiCrudActionCreator;

/**
 * Corp CRUD Action Creator with defined types.
 *
 * @param <T> the Corp API object type
 * @param <D> the backing Corp API
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 */
public class DefaultCorpCrudActionCreator<T extends ApiIdObject, D extends CorpCrudDataApiTemp<T, ?, ?>> extends DefaultDataApiCrudActionCreator<String, T, D> implements CorpCrudActionCreator<T, D> {

	/**
	 * @param type the entity type
	 * @param api the backing data API
	 */
	public DefaultCorpCrudActionCreator(final CorpEntityType type, D api) {
		super(type.getActionCreatorKey(), api);
	}
}

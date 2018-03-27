package com.github.bordertech.corpdir.web.ui.flux.store.temp;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.web.ui.CorpEntityType;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.temp.CorpCrudTreeDataApiTemp;
import com.github.bordertech.flux.crud.store.impl.DefaultDataApiCrudTreeStore;

/**
 * Default Corp Tree Store with backing API.
 *
 * @param <T> the CorpDir API Object
 * @param <D> the CorpDir data API type
 * @author Jonathan Austin
 * @author Aswin Kandula
 */
public class DefaultCorpCrudTreeStoreTemp<T extends ApiTreeable, D extends CorpCrudTreeDataApiTemp<T, ?, ?>> extends DefaultDataApiCrudTreeStore<String, String, T, D> implements CorpCrudTreeStoreTemp<T, D> {

	/**
	 * @param type the corp entity type
	 * @param api the backing API
	 */
	public DefaultCorpCrudTreeStoreTemp(final CorpEntityType type, final D api) {
		super(type.getStoreKey(), CorpEntityType.getLinkedCreators(type), api);
	}
}

package com.github.bordertech.corpdir.web.ui.flux.store.temp;

import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.web.ui.CorpEntityType;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.impl.SystemCtrlApi;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.temp.CorpCrudVersionDataApiTemp;
import com.github.bordertech.didums.Didums;

/**
 * Default Corp Store with backing API.
 *
 * @param <T> the CorpDir API Object
 * @param <D> the CorpDir data API type
 * 
 * @author Jonathan Austin
 * @author Aswin Kandula
 */
public class DefaultCorpCrudVersionStoreTemp<T extends ApiVersionable, D extends CorpCrudVersionDataApiTemp<T, ?, ?>> extends DefaultCorpCrudStoreTemp<T, D> implements CorpCrudVersionStoreTemp<T, D> {

	private static final SystemCtrlApi CTRL = Didums.getService(SystemCtrlApi.class);

	/**
	 * @param type the corp entity type
	 * @param api the backing API
	 */
	public DefaultCorpCrudVersionStoreTemp(final CorpEntityType type, final D api) {
		super(type, api);
	}

	@Override
	public Long getCurrentVersionId() {
		// TODO Should cache this and then make sure it ghets cleared when the version changes
		return CTRL.getCurrentVersion();
	}

	@Override
	protected String getCacheKey(final String action, final Object criteria) {
		// Append the version id
		return super.getCacheKey(action, criteria) + "-" + getCurrentVersionId();
	}
}
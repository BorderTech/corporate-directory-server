package com.github.bordertech.corpdir.web.ui.flux.store.impl;

import com.github.bordertech.corpdir.api.v1.model.UnitType;
import com.github.bordertech.corpdir.web.ui.CorpEntityType;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.impl.UnitTypeApi;
import com.github.bordertech.corpdir.web.ui.flux.store.temp.DefaultCorpCrudStoreTemp;
import javax.inject.Inject;

/**
 * UnitType Store with backing API implementation.
 *
 * @author jonathan
 */
public class UnitTypeStore extends DefaultCorpCrudStoreTemp<UnitType, UnitTypeApi> {

	/**
	 * @param api the backing API
	 */
	@Inject
	public UnitTypeStore(final UnitTypeApi api) {
		super(CorpEntityType.UNIT_TYPE, api);
	}
}

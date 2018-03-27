package com.github.bordertech.corpdir.web.ui.flux.store.impl;

import com.github.bordertech.corpdir.api.v1.model.VersionCtrl;
import com.github.bordertech.corpdir.web.ui.CorpEntityType;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.impl.VersionCtrlApi;
import com.github.bordertech.corpdir.web.ui.flux.store.temp.DefaultCorpCrudStoreTemp;
import javax.inject.Inject;

/**
 * VersionCtrl Store with backing API implementation.
 *
 * @author jonathan
 */
public class VersionCtrlStore extends DefaultCorpCrudStoreTemp<VersionCtrl, VersionCtrlApi> {

	/**
	 * @param api the backing API
	 */
	@Inject
	public VersionCtrlStore(final VersionCtrlApi api) {
		super(CorpEntityType.VERSION_CTRL, api);
	}
}

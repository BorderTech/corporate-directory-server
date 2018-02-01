package com.github.bordertech.corpdir.web.ui.store.impl;

import com.github.bordertech.corpdir.api.v1.model.VersionCtrl;
import com.github.bordertech.corpdir.web.ui.CorpEntityType;
import com.github.bordertech.corpdir.web.ui.dataapi.VersionCtrlApi;
import com.github.bordertech.corpdir.web.ui.store.VersionCtrlStore;
import javax.inject.Inject;

/**
 * Version Ctrl Store with backing API implementation.
 *
 * @author jonathan
 */
public class VersionCtrlStoreImpl extends DefaultCorpStore<VersionCtrl, VersionCtrlApi> implements VersionCtrlStore {

	/**
	 * @param api the backing API
	 */
	@Inject
	public VersionCtrlStoreImpl(final VersionCtrlApi api) {
		super(CorpEntityType.VERSION_CTRL, api);
	}
}

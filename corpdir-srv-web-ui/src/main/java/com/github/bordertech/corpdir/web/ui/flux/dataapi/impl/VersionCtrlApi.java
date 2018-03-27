package com.github.bordertech.corpdir.web.ui.flux.dataapi.impl;

import com.github.bordertech.corpdir.api.v1.model.VersionCtrl;
import com.github.bordertech.corpdir.modify.api.v1.VersionCtrlWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.VersionCtrlReadOnlyService;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.temp.DefaultCorpCrudDataApiTemp;
import javax.inject.Inject;

/**
 * VersionCtrl CRUD API implementation.
 *
 * @author jonathan
 */
public class VersionCtrlApi extends DefaultCorpCrudDataApiTemp<VersionCtrl, VersionCtrlReadOnlyService, VersionCtrlWriteService> {

	@Inject
	public VersionCtrlApi(final VersionCtrlReadOnlyService readService, final VersionCtrlWriteService writeService) {
		super(VersionCtrl.class, readService, writeService);
	}
}

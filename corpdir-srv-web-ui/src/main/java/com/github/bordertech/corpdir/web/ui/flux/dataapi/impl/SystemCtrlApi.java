package com.github.bordertech.corpdir.web.ui.flux.dataapi.impl;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.SystemCtrl;
import com.github.bordertech.corpdir.modify.api.v1.SystemCtrlWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.SystemCtrlReadOnlyService;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.DefaultCorpCrudDataApi;
import javax.inject.Inject;

/**
 * SystemCtrl CRUD API implementation.
 *
 * @author jonathan
 */
public class SystemCtrlApi extends DefaultCorpCrudDataApi<SystemCtrl, SystemCtrlReadOnlyService, SystemCtrlWriteService> {

	@Inject
	public SystemCtrlApi(final SystemCtrlReadOnlyService readService, final SystemCtrlWriteService writeService) {
		super(SystemCtrl.class, readService, writeService);
	}

	public Long getCurrentVersion() {
		DataResponse<Long> resp = getReadService().getCurrentVersion();
		return resp.getData();
	}

}

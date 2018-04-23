package com.github.bordertech.corpdir.web.ui.flux.dataapi.impl;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.SystemCtrl;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.DefaultCorpCrudDataApi;
import javax.inject.Inject;
import com.github.bordertech.corpdir.api.v1.SystemCtrlService;

/**
 * SystemCtrl CRUD API implementation.
 *
 * @author jonathan
 */
public class SystemCtrlApi extends DefaultCorpCrudDataApi<SystemCtrl, SystemCtrlService> {

	@Inject
	public SystemCtrlApi(final SystemCtrlService service) {
		super(SystemCtrl.class, service);
	}

	public Long getCurrentVersion() {
		DataResponse<Long> resp = getService().getCurrentVersion();
		return resp.getData();
	}

}

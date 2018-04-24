package com.github.bordertech.corpdir.api.v1;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicIdService;
import com.github.bordertech.corpdir.api.v1.model.SystemCtrl;

/**
 * System Control record read and write service.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public interface SystemCtrlService extends SystemCtrlReadOnlyService, BasicIdService<SystemCtrl> {

	/**
	 * @param versionId
	 * @return the current version details
	 */
	DataResponse<Long> setCurrentVersion(final Long versionId);
}

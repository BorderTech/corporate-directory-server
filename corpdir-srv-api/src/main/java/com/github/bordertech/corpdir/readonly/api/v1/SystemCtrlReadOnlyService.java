package com.github.bordertech.corpdir.readonly.api.v1;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.readonly.service.BasicIdReadOnlyService;
import com.github.bordertech.corpdir.api.v1.model.SystemCtrl;

/**
 * System Control record read-only service.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface SystemCtrlReadOnlyService extends BasicIdReadOnlyService<SystemCtrl> {

	/**
	 * @return the current version details
	 */
	DataResponse<Long> getCurrentVersion();
}

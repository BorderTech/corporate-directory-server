package com.github.bordertech.corpdir.modify.api.v1;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.modify.service.BasicIdWriteService;
import com.github.bordertech.corpdir.api.v1.model.SystemCtrl;

/**
 * System Control record write (modifiable) service.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface SystemCtrlWriteService extends BasicIdWriteService<SystemCtrl> {

	/**
	 * @param versionId
	 * @return the current version details
	 */
	DataResponse<Long> setCurrentVersion(final Long versionId);
}

package com.github.bordertech.corpdir.api.v1;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicIdService;
import com.github.bordertech.corpdir.api.v1.model.SystemCtrl;
import com.github.bordertech.corpdir.modify.api.v1.SystemCtrlWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.SystemCtrlReadOnlyService;

/**
 * System Control record service.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 * @deprecated split into {@link SystemCtrlReadOnlyService} and {@link SystemCtrlWriteService}
 */
@Deprecated
public interface SystemCtrlService extends BasicIdService<SystemCtrl> {

	/**
	 * @return the current version details
	 * @deprecated use {@link SystemCtrlReadOnlyService#getCurrentVersion() } instead.
	 */
	@Deprecated
	DataResponse<Long> getCurrentVersion();

	/**
	 * @param versionId
	 * @return the current version details
	 * @deprecated use {@link SystemCtrlWriteService#setCurrentVersion(java.lang.Long) } instead.
	 */
	@Deprecated
	DataResponse<Long> setCurrentVersion(final Long versionId);

}

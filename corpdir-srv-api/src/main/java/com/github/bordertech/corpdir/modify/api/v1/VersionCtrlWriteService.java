package com.github.bordertech.corpdir.modify.api.v1;

import com.github.bordertech.corpdir.api.response.BasicResponse;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.modify.BasicIdWriteService;
import com.github.bordertech.corpdir.api.v1.model.VersionCtrl;

/**
 * Version Control write (modifiable) Service Interface.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface VersionCtrlWriteService extends BasicIdWriteService<VersionCtrl> {

	/**
	 * Short hand create function.
	 *
	 * @param description the version description
	 * @return a new version id
	 */
	DataResponse<Long> createVersion(final String description);

	/**
	 * Copy a version.
	 *
	 * @param fromId copy from version id
	 * @param toId copy to version id
	 * @param copySystem copy the system details
	 * @param copyCustom copy the custom details
	 * @return basic response if successful or error occurred
	 */
	BasicResponse copyVersion(final Long fromId, final Long toId, boolean copySystem, boolean copyCustom);
}

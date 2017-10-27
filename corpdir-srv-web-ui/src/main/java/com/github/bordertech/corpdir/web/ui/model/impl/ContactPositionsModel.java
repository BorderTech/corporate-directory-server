package com.github.bordertech.corpdir.web.ui.model.impl;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.ContactService;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.web.ui.model.SearchVersionKey;
import com.github.bordertech.corpdir.web.ui.util.LocatorUtil;
import com.github.bordertech.wcomponents.lib.app.model.SearchModel;
import java.util.List;

/**
 * Contact positions model.
 *
 * @author jonathan
 */
public class ContactPositionsModel implements SearchModel<SearchVersionKey, Position> {

	private static final ContactService SERVICE = LocatorUtil.getService(ContactService.class);

	@Override
	public List<Position> retrieveCollection(final SearchVersionKey criteria) {
		Long versionId = criteria.getVersionId();
		String key = criteria.getId();
		DataResponse<List<Position>> resp;
		if (versionId == null) {
			resp = SERVICE.getPositions(key);
		} else {
			resp = SERVICE.getPositions(versionId, key);
		}
		return resp.getData();
	}

}

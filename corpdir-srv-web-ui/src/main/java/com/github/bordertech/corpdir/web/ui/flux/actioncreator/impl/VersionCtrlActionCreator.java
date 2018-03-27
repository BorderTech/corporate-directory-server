package com.github.bordertech.corpdir.web.ui.flux.actioncreator.impl;

import com.github.bordertech.corpdir.api.v1.model.VersionCtrl;
import com.github.bordertech.corpdir.web.ui.CorpEntityType;
import com.github.bordertech.corpdir.web.ui.flux.actioncreator.temp.DefaultCorpCrudActionCreatorTemp;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.impl.VersionCtrlApi;
import javax.inject.Inject;

/**
 * Version Ctrl action creator implementation.
 *
 * @author jonathan
 */
public class VersionCtrlActionCreator extends DefaultCorpCrudActionCreatorTemp<VersionCtrl, VersionCtrlApi> {

	/**
	 * @param api the backing API
	 */
	@Inject
	public VersionCtrlActionCreator(final VersionCtrlApi api) {
		super(CorpEntityType.VERSION_CTRL, api);
	}
}

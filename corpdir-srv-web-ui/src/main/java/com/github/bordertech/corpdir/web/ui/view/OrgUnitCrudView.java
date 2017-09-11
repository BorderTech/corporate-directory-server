package com.github.bordertech.corpdir.web.ui.view;

import com.github.bordertech.corpdir.web.ui.panel.OrgUnitPanel;
import com.github.bordertech.wcomponents.lib.app.view.combo.DefaultCrudView;

/**
 * Org Unit crud view.
 *
 * @author jonathan
 */
public class OrgUnitCrudView extends DefaultCrudView {

	public OrgUnitCrudView() {
		super("Org Unit", new OrgUnitPanel());
		setSearchModelKey("orgunit.search");
		setActionModelKey("orgunit.action");
	}

}

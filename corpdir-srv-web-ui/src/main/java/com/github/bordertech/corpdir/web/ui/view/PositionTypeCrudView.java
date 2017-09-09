package com.github.bordertech.corpdir.web.ui.view;

import com.github.bordertech.corpdir.web.ui.panel.BasicApiKeyPanel;
import com.github.bordertech.wcomponents.lib.app.combo.DefaultCrudView;

/**
 * Unit type crud view.
 *
 * @author jonathan
 */
public class PositionTypeCrudView extends DefaultCrudView {

	public PositionTypeCrudView() {
		super("Position Type", new BasicApiKeyPanel());
		setSearchModelKey("positiontype.search");
		setActionModelKey("positiontype.action");
	}

}

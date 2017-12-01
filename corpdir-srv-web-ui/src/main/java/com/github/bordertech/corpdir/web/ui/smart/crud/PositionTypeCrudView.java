package com.github.bordertech.corpdir.web.ui.smart.crud;

import com.github.bordertech.corpdir.api.v1.model.PositionType;
import com.github.bordertech.corpdir.web.ui.dumb.BasicApiKeyPanel;
import com.github.bordertech.flux.wc.view.smart.crud.DefaultCrudSmartView;

/**
 * Unit type crud view.
 *
 * @author jonathan
 */
public class PositionTypeCrudView extends DefaultCrudSmartView<String, PositionType> {

	public PositionTypeCrudView() {
		super("PT", "Position Type", new BasicApiKeyPanel("PL"));
	}

}

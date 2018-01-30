package com.github.bordertech.corpdir.web.ui.smart.card;

import com.github.bordertech.corpdir.api.v1.model.VersionCtrl;
import com.github.bordertech.corpdir.web.ui.config.CardType;
import com.github.bordertech.corpdir.web.ui.dumb.BasicApiIdPanel;
import com.github.bordertech.corpdir.web.ui.smart.crud.DefaultCorpCrudSmartView;

/**
 * Version Ctrl crud view.
 *
 * @author jonathan
 */
public class VersionCtrlCardView extends AppSecureCrudCardView<VersionCtrl> {

	public VersionCtrlCardView() {
		super("VC", CardType.VERSION_CTRL, new DefaultCorpCrudSmartView<VersionCtrl>("SV", "Version Ctrl", new BasicApiIdPanel("PL")));
	}

}

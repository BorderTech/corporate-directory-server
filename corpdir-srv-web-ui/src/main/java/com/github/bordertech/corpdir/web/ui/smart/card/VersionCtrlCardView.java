package com.github.bordertech.corpdir.web.ui.smart.card;

import com.github.bordertech.corpdir.web.ui.flux.impl.DefaultCorpSecureCrudCardView;
import com.github.bordertech.corpdir.api.v1.model.VersionCtrl;
import com.github.bordertech.corpdir.web.ui.CardType;
import com.github.bordertech.corpdir.web.ui.dumb.panel.VersionCtrlPanel;
import com.github.bordertech.corpdir.web.ui.flux.impl.DefaultCorpCrudSmartView;

/**
 * Version Ctrl crud view.
 *
 * @author jonathan
 */
public class VersionCtrlCardView extends DefaultCorpSecureCrudCardView<VersionCtrl> {

	public VersionCtrlCardView() {
		super("VC", CardType.VERSION_CTRL, new DefaultCorpCrudSmartView<VersionCtrl>("SV", "Version Ctrl", new VersionCtrlPanel("PL")));
	}

}

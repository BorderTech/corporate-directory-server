package com.github.bordertech.corpdir.web.ui.smart.card;

import com.github.bordertech.corpdir.api.v1.model.PositionType;
import com.github.bordertech.corpdir.web.ui.config.CardType;
import com.github.bordertech.corpdir.web.ui.dumb.BasicApiKeyPanel;
import com.github.bordertech.corpdir.web.ui.smart.crud.DefaultCorpCrudSmartView;

/**
 * Position type crud view.
 *
 * @author jonathan
 */
public class PositionTypeCardView extends AppSecureCrudCardView<PositionType> {

	public PositionTypeCardView() {
		super("PT", CardType.POSITION_TYPE, new DefaultCorpCrudSmartView<PositionType>("SV", "Position Type", new BasicApiKeyPanel("PL")));
	}

}

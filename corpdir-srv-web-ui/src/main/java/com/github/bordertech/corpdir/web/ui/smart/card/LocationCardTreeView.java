package com.github.bordertech.corpdir.web.ui.smart.card;

import com.github.bordertech.corpdir.web.ui.flux.impl.DefaultCorpSecureCrudCardView;
import com.github.bordertech.corpdir.api.v1.model.Location;
import com.github.bordertech.corpdir.web.ui.CardType;
import com.github.bordertech.corpdir.web.ui.dumb.panel.LocationPanel;
import com.github.bordertech.corpdir.web.ui.flux.impl.DefaultCorpCrudTreeSmartView;

/**
 * Location CRUD tree view.
 *
 * @author jonathan
 */
public class LocationCardTreeView extends DefaultCorpSecureCrudCardView<Location> {

	public LocationCardTreeView() {
		super("LOC", CardType.LOCATION, new DefaultCorpCrudTreeSmartView<Location>("SV", "Location", new LocationPanel("PL")));
	}

}
